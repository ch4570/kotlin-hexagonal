package com.okestro.kcredit.idp.user.application.port.`in`.model

data class ModifyUserCommand(
    val loginPassword: String,
    val name: String,
    val department: String
)