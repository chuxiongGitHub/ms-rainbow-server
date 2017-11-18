package com.rainbow.ms.service

import com.rainbow.ms.entity.User
import com.rainbow.ms.security.UserAuthenticationToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthService {


    @Autowired
    private lateinit var redisTemplate: StringRedisTemplate

    @Autowired
    private lateinit var userService: UserService


    // 获取client 的app type
    private fun getClient() = (SecurityContextHolder.getContext().authentication as UserAuthenticationToken).client!!


    /**
     * 根据token获取用户
     */
    fun loadUserByToken(token: String): User? {
        val keys = redisTemplate.keys("${getClient().clientId}:token:*:$token")

        val mobiles = redisTemplate.opsForValue().multiGet(keys)

        if (mobiles.size != 1) {
            redisTemplate.delete(keys)
            throw BadCredentialsException("授权失败")
        } else {
            return userService.loadUserByMobile(mobiles.first())
        }


    }

}