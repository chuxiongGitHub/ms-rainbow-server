package com.rainbow.ms.commons

import com.baomidou.mybatisplus.mapper.BaseMapper
import com.baomidou.mybatisplus.service.impl.ServiceImpl
import java.io.Serializable

open class SuperService<M : BaseMapper<T>, T : SuperEntity> : ServiceImpl<M, T>(), ISuperService<T> {

    override fun beforeInsert(entity: T) {
        entity.status = 1
    }

    override fun beforeUpdate(current: T?, entity: T) {
        current ?: throw AppError("更新出错，没有找到记录")
    }

    override fun beforeDelete(current: T?) {
        current ?: throw AppError("删除错误，没有找到记录")
    }

    override fun afterInsert(entity: T) {
    }

    /**
     * 重写新增数据方法
     */
    override fun insert(entity: T): Boolean {
        beforeInsert(entity)

        val result = super.insert(entity)

        if (result) afterInsert(entity)

        return result
    }

    /**
     * 重写删除数据方法
     */
    override fun deleteById(id: Serializable?): Boolean {
        val current = selectById(id)

        beforeDelete(current)

        return super.deleteById(id)
    }

    /**
     * 重写更新数据方法
     */
    override fun updateById(entity: T): Boolean {

        val current = selectById(entity.id)

        beforeUpdate(current, entity)

        return super.updateById(entity)
    }
}