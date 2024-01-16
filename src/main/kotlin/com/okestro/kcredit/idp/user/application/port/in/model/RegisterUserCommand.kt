package com.okestro.kcredit.idp.user.application.port.`in`.model

import com.okestro.kcredit.idp.user.domain.Role

data class RegisterUserCommand(
    val loginId: String,
    val loginPassword: String,
    val name: String,
    val department: String,
    val role: Role
)