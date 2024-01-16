package com.okestro.kcredit.idp.ci.adapter.`in`.web

import com.okestro.kcredit.idp.ci.application.port.`in`.usecase.ExecuteBuildUseCase
import com.okestro.kcredit.idp.common.dto.BaseResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ExecuteBuildController(
    private val executeBuildUseCase: ExecuteBuildUseCase
) {

    @PostMapping("/api/jenkins/jobs/{jobName}")
    fun executeBuild(@PathVariable("jobName") jobName: String) : BaseResponse<Nothing> {
        executeBuildUseCase.executeBuildUseCase(jobName)
        return BaseResponse.ok()
    }
}