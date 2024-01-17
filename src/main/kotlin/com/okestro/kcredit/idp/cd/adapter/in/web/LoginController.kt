package com.okestro.kcredit.idp.cd.adapter.`in`.web

import com.okestro.kcredit.idp.cd.application.port.`in`.LoginUseCase
import com.okestro.kcredit.idp.common.dto.BaseResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
    private val loginUseCase: LoginUseCase
) {
    @PostMapping("/api/v1/session")
    fun getArgoCdToken() =
        BaseResponse.ok(loginUseCase.getArgoCdToken())
}