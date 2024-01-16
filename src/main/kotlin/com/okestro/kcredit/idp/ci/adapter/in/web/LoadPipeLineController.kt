package com.okestro.kcredit.idp.ci.adapter.`in`.web

import com.okestro.kcredit.idp.ci.application.port.`in`.usecase.LoadPipelineUseCase
import com.okestro.kcredit.idp.common.dto.BaseResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoadPipeLineController(
    private val loadPipelineUseCase: LoadPipelineUseCase
) {

    @GetMapping("/api/jenkins/pipeline")
    fun loadPipelineList() =
        BaseResponse.ok(loadPipelineUseCase.loadPipeLineList())
}