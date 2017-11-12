package com.rainbow.ms

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class MsRainbowServerApplication

fun main(args: Array<String>) {
    SpringApplication.run(MsRainbowServerApplication::class.java, *args)
}
