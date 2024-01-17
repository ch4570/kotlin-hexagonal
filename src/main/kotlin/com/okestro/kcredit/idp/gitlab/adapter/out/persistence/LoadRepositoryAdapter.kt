package com.okestro.kcredit.idp.gitlab.adapter.out.persistence

import com.fasterxml.jackson.databind.ObjectMapper
import com.okestro.kcredit.idp.common.annotation.ExternalSystemAdapter
import com.okestro.kcredit.idp.common.config.GitlabProperties
import com.okestro.kcredit.idp.common.config.RestClientConfig
import com.okestro.kcredit.idp.gitlab.application.port.out.LoadRepositoryPort
import com.okestro.kcredit.idp.gitlab.application.port.out.model.LoadRepositoryResponse

@ExternalSystemAdapter
class LoadRepositoryAdapter (
    private val mapper: ObjectMapper,
    private val restClientConfig: RestClientConfig,
    private val gitlabProperties: GitlabProperties
) : LoadRepositoryPort{
    override fun loadRepositoryList(): LoadRepositoryResponse? {
        val connectGitLab = restClientConfig.connectGitLab()

        val toEntity2 = connectGitLab
            .get()
            .uri("/projects?owned=true")
            .header("PRIVATE-TOKEN", gitlabProperties.token)
            .retrieve()
            .body(String::class.java)


        return mapper.readValue(toEntity2, LoadRepositoryResponse::class.java)
    }


}