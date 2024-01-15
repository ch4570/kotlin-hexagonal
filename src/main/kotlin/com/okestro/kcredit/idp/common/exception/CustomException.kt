package com.okestro.kcredit.idp.common.exception

class CustomException(
    val errorCode: ErrorCode
) : RuntimeException()