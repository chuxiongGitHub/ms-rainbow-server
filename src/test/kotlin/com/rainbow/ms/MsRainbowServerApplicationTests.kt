package com.rainbow.ms

import com.rainbow.ms.entity.Client
import com.rainbow.ms.mapper.ClientMapper
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class MsRainbowServerApplicationTests {

    private val passwordEncode by lazy { BCryptPasswordEncoder() }


    @Autowired
    private lateinit var mapper: ClientMapper

    @Test
    fun contextLoads() {
    }

    @Test
    fun createClient() {
        val client = Client()

        client.clientId = "rainbow"
        client.clientType = "client"
        client.clientSecret = passwordEncode.encode("admin123")

        mapper.insert(client)


    }

}
