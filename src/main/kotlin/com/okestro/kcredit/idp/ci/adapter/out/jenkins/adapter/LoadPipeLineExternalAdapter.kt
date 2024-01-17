package com.okestro.kcredit.idp.ci.adapter.out.jenkins.adapter

import com.fasterxml.jackson.databind.ObjectMapper
import com.okestro.kcredit.idp.ci.application.port.out.LoadPipeLinePort
import com.okestro.kcredit.idp.ci.application.port.out.model.Hudson
import com.okestro.kcredit.idp.common.annotation.ExternalSystemAdapter
import com.okestro.kcredit.idp.common.properties.JenkinsProperties
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestClient
import java.util.*


@ExternalSystemAdapter
class LoadPipeLineExternalAdapter(
    private val jenkinsProperties: JenkinsProperties,
    private val objectMapper: ObjectMapper
) : LoadPipeLinePort {

    override fun loadPipeLineList(): Hudson {
      val client = RestClient.builder()
          .baseUrl("${jenkinsProperties.baseUrl}/api/json")
          .defaultHeader(HttpHeaders.AUTHORIZATION, createBasicAuth())
          .build()

        val result = client
            .method(HttpMethod.GET)
            .retrieve()
            .body(String::class.java) ?: "No Data"

        return objectMapper.readValue(result, Hudson::class.java)
    }

    private fun createBasicAuth() : String {
        val credential = "${jenkinsProperties.id}:${jenkinsProperties.token}"
        return "Basic ${Base64.getEncoder().encodeToString(credential.toByteArray())}"
    }
}