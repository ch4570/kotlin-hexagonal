package com.okestro.kcredit.idp.cd.adapter.`in`.web

import com.okestro.kcredit.idp.cd.application.port.`in`.usecase.LoadApplicationUseCase
import com.okestro.kcredit.idp.common.dto.BaseResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoadApplicationController(
    private val loadApplicationUseCase: LoadApplicationUseCase
) {
    @GetMapping("/api/v1/applications")
    fun loadAllApplications() =
        BaseResponse.ok(loadApplicationUseCase.loadAllApplications())

}