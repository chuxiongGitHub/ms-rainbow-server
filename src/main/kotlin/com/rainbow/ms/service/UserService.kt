package com.rainbow.ms.service

import com.baomidou.mybatisplus.mapper.EntityWrapper
import com.rainbow.ms.commons.SuperService
import com.rainbow.ms.entity.User
import com.rainbow.ms.mapper.UserMapper
import com.rainbow.ms.security.UserAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
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


    /**
     * 通过手机号获取用户
     */
    fun loadUserByMobile(mobile: String): User? {
        val user = selectOne(EntityWrapper<User>().apply {
            eq("user_.mobile", mobile)
            eq("user_.type", getClient().clientType)
        }) ?: return null
        val roles = baseMapper.getRoles(user.id!!)

        val permissions = baseMapper.getPermissions(user.id!!)

        user.authorities = setOf(*roles.toTypedArray(), *permissions.toTypedArray(), user.role?.code)
                .filterNotNull()
                .map { SimpleGrantedAuthority(it) }

        return user
    }
}