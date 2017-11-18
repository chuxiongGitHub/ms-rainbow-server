package com.rainbow.ms.commons

import org.slf4j.LoggerFactory
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletResponse

@RestControllerAdvice
class ErrorHandler {

    private val logger by lazy { LoggerFactory.getLogger(ErrorHandler::class.java) }


    @ExceptionHandler
    fun handle(ex: Exception, response: HttpServletResponse) {
        val raw = Tools.getRawError(ex)

        when (raw) {
            is AuthenticationException, is AccessDeniedException -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, raw.message)

            is AppError, is IllegalArgumentException, is HttpRequestMethodNotSupportedException ->
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, raw.message)

            else -> {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, raw.message)
                logger.error(ex.message, ex)
            }
        }
    }
}