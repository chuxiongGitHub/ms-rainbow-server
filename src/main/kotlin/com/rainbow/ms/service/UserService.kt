package com.rainbow.ms.service

import com.rainbow.ms.commons.SuperService
import com.rainbow.ms.entity.User
import com.rainbow.ms.mapper.UserMapper
import com.rainbow.ms.security.UserAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService : SuperService<UserMapper, User>() {

    private val passwordEncoder by lazy { BCryptPasswordEncoder() }


    //获取 client 的 app type
    private fun getClient() = (SecurityContextHolder.getContext().authentication as UserAuthenticationToken).client!!

    //校验密码
    fun verifyPassword(rawPassword: String, encodePassword: String, salt: String) =
            passwordEncoder.matches(rawPassword + salt, encodePassword)
}