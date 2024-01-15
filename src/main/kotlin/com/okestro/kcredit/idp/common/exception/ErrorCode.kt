package com.okestro.kcredit.idp.common.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val serial: String,
    val message: String,
) {

    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "ERROR_01", "해당하는 회원이 없습니다. 확인 후 다시 시도해주시기 바랍니다.")
}