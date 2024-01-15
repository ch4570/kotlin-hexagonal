package com.okestro.kcredit.idp.user.application.port.`in`.model

data class LoginUserCommand(
    val loginId: String,
    val loginPassword: String
)