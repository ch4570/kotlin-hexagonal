package com.okestro.kcredit.idp.gitlab.adapter.`in`.web

import com.okestro.kcredit.idp.common.dto.BaseResponse
import com.okestro.kcredit.idp.gitlab.application.port.`in`.usecase.LoadRepositoryUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoadRepositoryController (
    private val loadRepositoryUseCase: LoadRepositoryUseCase
){

    @GetMapping("/api/gitlab/list")
    fun loadRepositoryList() =
        BaseResponse.ok(loadRepositoryUseCase.loadRepositoryList())

}