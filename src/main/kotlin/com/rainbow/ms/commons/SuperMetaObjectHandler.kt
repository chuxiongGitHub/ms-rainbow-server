package com.rainbow.ms.commons

import com.baomidou.mybatisplus.mapper.MetaObjectHandler
import org.apache.ibatis.reflection.MetaObject
import java.util.*

open class SuperMetaObjectHandler : MetaObjectHandler() {

    override fun insertFill(metaObject: MetaObject?) {
        setFieldValByName("createTime", Date(), metaObject) //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateFill(metaObject: MetaObject?) {
        setFieldValByName("updateTime", Date(), metaObject) //To change body of created functions use File | Settings | File Templates.
    }
}