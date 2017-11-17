package com.rainbow.ms.entity

import com.baomidou.mybatisplus.annotations.TableName
import com.rainbow.ms.commons.SuperEntity

@TableName("r_role")
data class Role(

        var code: String? = null,

        var name: String? = null
) : SuperEntity()