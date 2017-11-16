package com.rainbow.ms.commons

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletResponse

@RestControllerAdvice
class ErrorHandler {

    private val logger by lazy { LoggerFactory.getLogger(ErrorHandler::class.java) }


    @ExceptionHandler
    fun handle(ex: Exception, response: HttpServletResponse) {

    }
}