package com.rainbow.ms.entity

import com.baomidou.mybatisplus.annotations.TableName
import com.rainbow.ms.commons.SuperEntity

@TableName("r_driver")
data class Driver(
        var username: String? = null,

        var alias: String? = null
) : SuperEntity()