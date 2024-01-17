package com.okestro.kcredit.idp.gitlab.adapter.out.vcs.client

import com.okestro.kcredit.idp.gitlab.application.port.out.model.LoadAllRepoResponse
import com.okestro.kcredit.idp.gitlab.application.port.out.model.LoadDetailRepoResponse
import com.okestro.kcredit.idp.gitlab.common.config.GitlabFeignConfig
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "gitlabClient", url = "\${gitlab.url}", configuration = [GitlabFeignConfig::class])

interface GitlabClient {

    @GetMapping(value = ["/projects/{repoId}/repository/tree"])
    fun loadGitlabRepoDetail(
        @PathVariable("repoId") repoId: Int
    ): List<LoadDetailRepoResponse>

    @GetMapping(value = ["/projects?owned=true"])
    fun loadGitlabRepoList() : List<LoadAllRepoResponse>



}