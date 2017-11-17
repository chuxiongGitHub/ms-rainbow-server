package com.rainbow.ms.entity

import com.baomidou.mybatisplus.annotations.TableField
import com.baomidou.mybatisplus.annotations.TableName
import com.rainbow.ms.commons.SuperEntity
import org.springframework.security.core.GrantedAuthority
import java.security.Principal

@TableName("r_client")
data class Client(

        /**
         * 应用id
         */
        var clientId: String? = null,

        /**
         * 应用秘钥
         */
        var clientSecret: String? = null,

        /**
         * 应用类型
         */
        var clientType: String? = null,

        /**
         * 权限列表
         */
        @TableField(exist = false)
        var authorities: List<GrantedAuthority>? = null
) : SuperEntity(), Principal {
    override fun getName() = clientId
}