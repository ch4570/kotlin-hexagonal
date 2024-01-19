package com.okestro.kcredit.idp.ci.adapter.out.jenkins.adapter

import com.okestro.kcredit.idp.ci.application.port.out.ExecuteBuildPort
import com.okestro.kcredit.idp.common.annotation.ExternalSystemAdapter
import com.okestro.kcredit.idp.ci.common.properties.JenkinsProperties
import com.okestro.kcredit.idp.ci.common.util.JenkinsClientProvider
import com.okestro.kcredit.idp.common.exception.CustomException
import com.okestro.kcredit.idp.common.exception.ErrorCode.*
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate

@ExternalSystemAdapter
class ExecuteBuildExternalAdapter(
    private val restTemplate: RestTemplate,
    private val jenkinsProperties: JenkinsProperties
) : ExecuteBuildPort {

    override fun executeBuild(jobName: String) {
        val authHeader = JenkinsClientProvider.createJenkinsAuthHeader(jenkinsProperties.id, jenkinsProperties.token)

        val status = restTemplate.exchange("${jenkinsProperties.baseUrl}/job/$jobName/build", HttpMethod.POST,
            HttpEntity<String>(authHeader),String::class.java)
            .statusCode.value()

        if (status != 201) throw CustomException(JENKINS_BUILD_FAIL)
    }

}