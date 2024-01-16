package com.okestro.kcredit.idp.ci.adapter.out.jenkins.adapter

import com.okestro.kcredit.idp.ci.application.port.out.ExecuteBuildPort
import com.okestro.kcredit.idp.common.annotation.ExternalSystemAdapter
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestClient
import java.util.*

@ExternalSystemAdapter
class ExecuteBuildExternalAdapter(
    @Value("\${jenkins.baseurl}")
    val jenkinsUrl: String
) : ExecuteBuildPort {

    override fun executeBuild(jobName: String) {
        val client = RestClient.builder()
            .baseUrl("${jenkinsUrl}/job/$jobName/build")
            .defaultHeader(HttpHeaders.AUTHORIZATION, createBasicAuth())
            .build()

        client
            .method(HttpMethod.POST)
            .retrieve()
    }


    private fun createBasicAuth() : String {
        val credential = "admin:111184d48b4fa2f1995724a4df2be59370"
        return "Basic ${Base64.getEncoder().encodeToString(credential.toByteArray())}"
    }
}