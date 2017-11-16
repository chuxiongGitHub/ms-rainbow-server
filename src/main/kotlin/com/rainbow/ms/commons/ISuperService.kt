package com.rainbow.ms.commons

import com.baomidou.mybatisplus.service.IService

interface ISuperService<T> : IService<T> {


    fun beforeInsert(entity: T)

    fun beforeUpdate(current: T?, entity: T)

    fun beforeDelete(current: T?)

    fun afterInsert(entity: T)
}