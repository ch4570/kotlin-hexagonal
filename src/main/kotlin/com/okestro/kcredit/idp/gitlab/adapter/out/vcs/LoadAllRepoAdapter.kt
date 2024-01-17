package com.okestro.kcredit.idp.gitlab.adapter.out.vcs

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.okestro.kcredit.idp.common.annotation.ExternalSystemAdapter
import com.okestro.kcredit.idp.gitlab.adapter.out.vcs.client.GitlabClient
import com.okestro.kcredit.idp.gitlab.common.properties.GitlabProperties
import com.okestro.kcredit.idp.gitlab.application.port.out.LoadAllRepoPort
import com.okestro.kcredit.idp.gitlab.application.port.out.model.LoadAllRepoResponse

@ExternalSystemAdapter
class LoadAllRepoAdapter (
    private val gitlabClient: GitlabClient,
) : LoadAllRepoPort{
    override fun loadAllRepoList(): List<LoadAllRepoResponse> {
        return gitlabClient.loadGitlabRepoList()
    }

}