package com.rainbow.ms.service

import com.baomidou.mybatisplus.mapper.EntityWrapper
import com.rainbow.ms.commons.SuperService
import com.rainbow.ms.entity.Client
import com.rainbow.ms.mapper.ClientMapper
import org.springframework.stereotype.Service


@Service
class ClientService : SuperService<ClientMapper, Client>() {

    fun loadClientByClientId(clientId: String): Client? =
            selectOne(EntityWrapper<Client>().apply { eq("clientId", clientId) })


    override fun beforeInsert(entity: Client) {
        super.beforeInsert(entity)
        entity.status = 1
    }
}