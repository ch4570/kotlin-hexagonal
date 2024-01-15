package com.okestro.kcredit.idp.common.exception

import com.okestro.kcredit.idp.common.dto.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalCatcher {

    @ExceptionHandler(CustomException::class)
    protected fun handleCustomException(customException: CustomException) : ResponseEntity<ErrorResponse> {
        val errorCode = customException.errorCode
        return ResponseEntity.status(errorCode.status)
            .body(ErrorResponse(
                serial = errorCode.serial,
                message = errorCode.message)
            )
    }
}