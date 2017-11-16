package com.rainbow.ms.commons

import com.baomidou.mybatisplus.annotations.TableField
import com.baomidou.mybatisplus.enums.FieldFill
import java.util.*

open class SuperEntity {

    /**
     * 数据库主键
     */
    var id: Long? = null

    /**
     * 状态 0:禁用 1:启用
     */
    var status: Int? = null

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    var createTime: Date? = null

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    var updateTime: Date? = null


}