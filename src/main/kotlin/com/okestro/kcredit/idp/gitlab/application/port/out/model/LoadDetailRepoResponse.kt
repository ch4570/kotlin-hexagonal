package com.okestro.kcredit.idp.gitlab.application.port.out.model

data class LoadDetailRepoResponse(
    val id: String,
    val name: String,
    val type: String,
    val path: String,
    val mode: String
)
