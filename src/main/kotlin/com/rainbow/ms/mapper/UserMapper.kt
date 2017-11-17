package com.rainbow.ms.mapper

import com.rainbow.ms.commons.SuperMapper
import com.rainbow.ms.entity.User
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface UserMapper : SuperMapper<User> {

    fun getRoles(@Param("id") id: Long): List<String>


    fun getPermissions(@Param("id") id: Long): List<String>


    fun addRoles(@Param("id") id: Long, @Param("roles") roles: List<String>)
}