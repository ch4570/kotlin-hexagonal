package com.okestro.kcredit.idp.ci.adapter.out.jenkins.adapter

import com.fasterxml.jackson.databind.ObjectMapper
import com.okestro.kcredit.idp.ci.application.port.out.LoadPipeLinePort
import com.okestro.kcredit.idp.ci.application.port.out.model.Hudson
import com.okestro.kcredit.idp.common.annotation.ExternalSystemAdapter
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestClient
import java.util.*


@ExternalSystemAdapter
class LoadPipeLineExternalAdapter(
    @Value("\${jenkins.baseurl}")
    val jenkinsUrl: String,
    private val objectMapper: ObjectMapper
) : LoadPipeLinePort {

    override fun loadPipeLineList(): Hudson {
      val client = RestClient.builder()
          .baseUrl("${jenkinsUrl}/api/json")
          .defaultHeader(HttpHeaders.AUTHORIZATION, createBasicAuth())
          .build()

        val result = client
            .method(HttpMethod.GET)
            .retrieve()
            .body(String::class.java) ?: "No Data"

        return objectMapper.readValue(result, Hudson::class.java)
    }

    private fun createBasicAuth() : String {
        val credential = "admin:111184d48b4fa2f1995724a4df2be59370"
        return "Basic ${Base64.getEncoder().encodeToString(credential.toByteArray())}"
    }
}