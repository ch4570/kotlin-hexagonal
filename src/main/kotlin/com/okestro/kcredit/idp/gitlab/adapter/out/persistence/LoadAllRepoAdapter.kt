package com.okestro.kcredit.idp.gitlab.adapter.out.persistence

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.okestro.kcredit.idp.common.annotation.ExternalSystemAdapter
import com.okestro.kcredit.idp.common.config.GitlabProperties
import com.okestro.kcredit.idp.common.config.RestClientConfig
import com.okestro.kcredit.idp.gitlab.application.port.out.LoadAllRepoPort
import com.okestro.kcredit.idp.gitlab.application.port.out.model.LoadAllRepoResponse

@ExternalSystemAdapter
class LoadAllRepoAdapter (
    private val mapper: ObjectMapper,
    private val restClientConfig: RestClientConfig,
    private val gitlabProperties: GitlabProperties
) : LoadAllRepoPort{
    override fun loadAllRepoList(): List<LoadAllRepoResponse> {
        val connectGitLab = restClientConfig.connectGitLab()

        val toEntity = connectGitLab
            .get()
            .uri("/projects?owned=true")
            .header("PRIVATE-TOKEN", gitlabProperties.token)
            .retrieve()
            .toEntity(String::class.java)

        return mapper.readValue(toEntity.body.toString())

    }


}