package com.rainbow.ms.security

import com.rainbow.ms.commons.ErrorHandler
import com.rainbow.ms.commons.SuperEntity
import com.rainbow.ms.entity.Client
import com.rainbow.ms.entity.User
import com.rainbow.ms.service.AuthService
import com.rainbow.ms.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.DigestUtils
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthFilter : OncePerRequestFilter() {

    @Autowired
    private lateinit var errorHandler: ErrorHandler

    @Autowired
    private lateinit var clientService: ClientService

    @Autowired
    private lateinit var authService: AuthService


    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

        if (WhiteList.check(request.requestURI)) return filterChain.doFilter(request, response)

        try {
            SecurityContextHolder.getContext().authentication = UserAuthenticationToken(getClient(request))

            val user = getUser(request)
            if (user != null) {
                SecurityContextHolder.getContext().authentication = UserAuthenticationToken(getClient(request), user)
            }
            filterChain.doFilter(request, response)
        } catch (ex: Exception) {
            errorHandler.handle(ex, response)
        }
    }


    private fun getUser(request: HttpServletRequest): User? {
        val token = request.getHeader("Authorization") ?: return null

        val user = authService.loadUserByToken(token) ?: return null

        preAuthenticationChecks(user)

        return user
    }

    private fun getClient(request: HttpServletRequest): Client {
        val clientId = getHeader(request, "X-AUTH-CLIENT")

        val client = clientService.loadClientByClientId(clientId) ?: throw BadCredentialsException("无效的X-AUTH-CLIENT请求头")

        preAuthenticationChecks(client)

        if (!client.clientSecret.isNullOrBlank()) {
            additionalAuthenticationChecks(client, request)
        }
        return client
    }

    private fun getHeader(request: HttpServletRequest, header: String) =
            request.getHeader(header) ?: throw BadCredentialsException("未找到$header")


    private fun preAuthenticationChecks(entity: SuperEntity) {
        when (entity.status) {
            -1 -> throw DisabledException("Client账号已禁用")
            0 -> throw  LockedException("Client账号已被禁用")
        }
    }

    private fun additionalAuthenticationChecks(client: Client, request: HttpServletRequest) {
        val secret = client.clientSecret!!

        val sign = getHeader(request, "X-AUTH-SIGN")

        val timestamp = getHeader(request, "X-AUTH-TIMESTAMP")

        val nonce = getHeader(request, "X-AUTH-NONCE")

        if (!isSignValid(sign, secret, timestamp, nonce)) throw BadCredentialsException("无效的 X-AUTH-SIGN")
    }

    private fun isSignValid(sign: String, secret: String, timestamp: String, nonce: String): Boolean {
        val raw = listOf(secret, timestamp, nonce).sorted().joinToString("")

        return DigestUtils.md5DigestAsHex(raw.toByteArray()) == sign
    }
}