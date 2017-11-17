package com.rainbow.ms.entity

import com.baomidou.mybatisplus.annotations.TableField
import com.baomidou.mybatisplus.annotations.TableName
import com.rainbow.ms.commons.SuperEntity
import org.springframework.security.core.GrantedAuthority
import java.security.Principal

@TableName("r_user")
data class User(
        /**
         * 手机号码
         */
        private var mobile: String? = null,

        /**
         * 登录密码
         */
        var password: String? = null,

        /**
         * 加密盐
         */
        var salt: String? = null,

        /**
         * 用户主角色
         */
        @TableField("role_id", el = "role.id")
        var role: Role? = null,

        /**
         * 用户信息
         */
        @TableField(exist = false)
        var userInfo: UserInfo? = null,

        /**
         * 权限列表
         */
        @TableField(exist = false)
        var authorities: List<GrantedAuthority>? = null
) : SuperEntity(), Principal {
    override fun getName() = mobile
}