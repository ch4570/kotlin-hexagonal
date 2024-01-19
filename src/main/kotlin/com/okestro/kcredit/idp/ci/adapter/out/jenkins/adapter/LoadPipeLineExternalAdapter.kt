package com.okestro.kcredit.idp.ci.adapter.out.jenkins.adapter

import com.okestro.kcredit.idp.ci.application.port.out.LoadPipeLinePort
import com.okestro.kcredit.idp.common.annotation.ExternalSystemAdapter
import com.okestro.kcredit.idp.ci.application.port.out.model.Hudson
import com.okestro.kcredit.idp.ci.common.properties.JenkinsProperties
import com.okestro.kcredit.idp.ci.common.util.JenkinsClientProvider
import com.okestro.kcredit.idp.common.exception.CustomException
import com.okestro.kcredit.idp.common.exception.ErrorCode.*
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate


@ExternalSystemAdapter
class LoadPipeLineExternalAdapter(
    private val restTemplate: RestTemplate,
    private val jenkinsProperties: JenkinsProperties
) : LoadPipeLinePort {

    override fun loadPipeLineList() : Hudson {
        val authHeader = JenkinsClientProvider.createJenkinsAuthHeader(jenkinsProperties.id, jenkinsProperties.token)

        return restTemplate.exchange("${jenkinsProperties.baseUrl}/api/json", HttpMethod.GET,
            HttpEntity<Hudson>(authHeader), Hudson::class.java)
            .body ?: throw CustomException(JENKINS_NO_DATA)
    }

}