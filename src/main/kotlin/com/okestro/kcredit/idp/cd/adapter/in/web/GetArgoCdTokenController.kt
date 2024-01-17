package com.okestro.kcredit.idp.cd.adapter.`in`.web

import com.okestro.kcredit.idp.cd.application.port.`in`.usecase.GetArgoCdTokenUseCase
import com.okestro.kcredit.idp.common.dto.BaseResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetArgoCdTokenController(
    private val getArgoCdTokenUseCase: GetArgoCdTokenUseCase
) {
    @PostMapping("/api/v1/session")
    fun getArgoCdToken() =
        BaseResponse.ok(getArgoCdTokenUseCase.getArgoCdToken())
}