package com.okestro.kcredit.idp.user.application.port.`in`.model

data class RegisterUserCommand(
    val loginId: String,
    val loginPassword: String,
    val name: String,
    val department: String
)