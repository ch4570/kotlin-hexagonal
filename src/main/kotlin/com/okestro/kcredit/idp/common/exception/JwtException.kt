package com.okestro.kcredit.idp.common.exception

class JwtException(
    val errorCode: ErrorCode
) : RuntimeException()