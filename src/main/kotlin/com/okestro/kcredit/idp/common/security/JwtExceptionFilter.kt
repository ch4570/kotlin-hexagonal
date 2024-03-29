package com.okestro.kcredit.idp.common.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.okestro.kcredit.idp.common.annotation.Filter
import com.okestro.kcredit.idp.common.dto.ErrorResponse
import com.okestro.kcredit.idp.common.exception.JwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.web.filter.OncePerRequestFilter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Filter
class JwtExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (e: JwtException) {
            response.status = 401
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            response.characterEncoding = "UTF-8"
            objectMapper.writeValue(response.writer,
                ErrorResponse(
                    serial = e.errorCode.serial,
                    message = e.errorCode.message,
                    timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            )
        }
    }
}