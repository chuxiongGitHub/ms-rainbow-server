package com.rainbow.ms.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloController {


    @Value("\${spring.application.name}")
    private lateinit var name: String

    @GetMapping
    fun hello() = mapOf("hello" to name)


}