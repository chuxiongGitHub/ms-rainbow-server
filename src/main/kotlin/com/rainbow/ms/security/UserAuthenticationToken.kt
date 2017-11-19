package com.rainbow.ms.security

import com.rainbow.ms.entity.Client
import com.rainbow.ms.entity.User
import org.springframework.security.authentication.AbstractAuthenticationToken

data class UserAuthenticationToken(
        val client: Client? = null,

        private val principal: User? = null
) : AbstractAuthenticationToken(principal?.authorities) {
    init {
        isAuthenticated = true
    }

    override fun getCredentials() = principal?.password

    override fun getPrincipal() = principal
}