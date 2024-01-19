package com.okestro.kcredit.idp.ci.adapter.out.jenkins.adapter

import com.okestro.kcredit.idp.ci.application.port.out.LoadPipeLinePort
import com.okestro.kcredit.idp.common.annotation.ExternalSystemAdapter
import com.okestro.kcredit.idp.ci.application.port.out.model.Hudson
import com.okestro.kcredit.idp.ci.common.properties.JenkinsProperties
import com.okestro.kcredit.idp.ci.common.util.JenkinsClientProvider
import com.okestro.kcredit.idp.common.exception.CustomException
import com.okestro.kcredit.idp.common.exception.ErrorCode.*
import org.springframework.http.HttpHeaders
import org.springframework.web.client.RestClient


@ExternalSystemAdapter
class LoadPipeLineExternalAdapter(
    private val jenkinsProperties: JenkinsProperties,
    private val restClient: RestClient
) : LoadPipeLinePort {

    override fun loadPipeLineList() : Hudson {

        return restClient
            .get()
            .uri("${jenkinsProperties.baseUrl}/api/json")
            .header(HttpHeaders.AUTHORIZATION, JenkinsClientProvider.createBasicAuth(jenkinsProperties.id, jenkinsProperties.token))
            .retrieve()
            .body(Hudson::class.java) ?: throw CustomException(JENKINS_NO_DATA)
    }

}