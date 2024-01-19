package com.okestro.kcredit.idp.ci.adapter.out.jenkins.adapter

import com.okestro.kcredit.idp.ci.application.port.out.ExecuteBuildPort
import com.okestro.kcredit.idp.common.annotation.ExternalSystemAdapter
import com.okestro.kcredit.idp.ci.common.properties.JenkinsProperties
import com.okestro.kcredit.idp.ci.common.util.JenkinsClientProvider
import com.okestro.kcredit.idp.common.exception.CustomException
import com.okestro.kcredit.idp.common.exception.ErrorCode.*
import org.springframework.http.HttpHeaders
import org.springframework.web.client.RestClient

@ExternalSystemAdapter
class ExecuteBuildExternalAdapter(
    private val restClient: RestClient,
    private val jenkinsProperties: JenkinsProperties
) : ExecuteBuildPort {

    override fun executeBuild(jobName: String) {
        val status = restClient
            .post()
            .uri("${jenkinsProperties.baseUrl}/job/$jobName/build")
            .header(HttpHeaders.AUTHORIZATION, JenkinsClientProvider.createBasicAuth(jenkinsProperties.id, jenkinsProperties.token))
            .retrieve()
            .toBodilessEntity()
            .statusCode.value()


        if (status != 201) throw CustomException(JENKINS_BUILD_FAIL)
    }

}