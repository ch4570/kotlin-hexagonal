package com.okestro.kcredit.idp.ci.adapter.out.jenkins.adapter

import com.okestro.kcredit.idp.ci.application.port.out.ExecuteBuildPort
import com.okestro.kcredit.idp.common.annotation.ExternalSystemAdapter
import com.okestro.kcredit.idp.common.properties.JenkinsProperties
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestClient
import java.util.*

@ExternalSystemAdapter
class ExecuteBuildExternalAdapter(
    private val jenkinsProperties: JenkinsProperties
) : ExecuteBuildPort {

    override fun executeBuild(jobName: String) {
        val client = RestClient.builder()
            .baseUrl("${jenkinsProperties.baseUrl}/job/$jobName/build")
            .defaultHeader(HttpHeaders.AUTHORIZATION, createBasicAuth())
            .build()

        client
            .method(HttpMethod.POST)
            .retrieve()
    }


    private fun createBasicAuth() : String {
        val credential = "${jenkinsProperties.id}:${jenkinsProperties.token}"
        return "Basic ${Base64.getEncoder().encodeToString(credential.toByteArray())}"
    }
}