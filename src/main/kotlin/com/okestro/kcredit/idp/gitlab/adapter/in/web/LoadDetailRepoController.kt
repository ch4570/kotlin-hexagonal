package com.okestro.kcredit.idp.gitlab.adapter.`in`.web

import com.okestro.kcredit.idp.common.dto.BaseResponse
import com.okestro.kcredit.idp.gitlab.application.port.`in`.usecase.LoadDetailRepoUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class LoadDetailRepoController (
    private val loadDetailRepoUseCase: LoadDetailRepoUseCase
){

    @GetMapping("/api/gitlab/detail/{repoId}")
    fun loadDetail(
        @PathVariable("repoId") repoId: Int
    ) =
        BaseResponse.ok(loadDetailRepoUseCase.loadDetailRepository(repoId))

}