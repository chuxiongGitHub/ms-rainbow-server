package com.rainbow.ms.service

import com.rainbow.ms.commons.SuperService
import com.rainbow.ms.entity.Driver
import com.rainbow.ms.mapper.DriverMapper
import org.springframework.stereotype.Service

@Service
class DriverService : SuperService<DriverMapper, Driver>() {

    override fun beforeInsert(entity: Driver) {
        super.beforeInsert(entity)
    }
}