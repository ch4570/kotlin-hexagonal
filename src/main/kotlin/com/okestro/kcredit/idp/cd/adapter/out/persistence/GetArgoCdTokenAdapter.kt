package com.okestro.kcredit.idp.cd.adapter.out.persistence

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.okestro.kcredit.idp.cd.application.port.out.GetArgoCdTokenPort
import com.okestro.kcredit.idp.cd.application.port.out.model.GetArgoCdTokenResponse
import com.okestro.kcredit.idp.common.annotation.ExternalSystemAdapter
import com.okestro.kcredit.idp.common.properties.ArgoCdProperties
import org.springframework.web.client.RestClient

@ExternalSystemAdapter
class GetArgoCdTokenAdapter (
    private val mapper: ObjectMapper,
    private val argoCdProperties: ArgoCdProperties
) : GetArgoCdTokenPort {
    override fun getArgoCdToken(): GetArgoCdTokenResponse {
        val restClient = RestClient.builder().baseUrl(argoCdProperties.baseUrl).build()

        val token = restClient
            .post()
            .uri("/api/v1/session")
            .body("""{"username":"${argoCdProperties.id}","password":"${argoCdProperties.password}"}""")
            .retrieve()
            .toEntity(String::class.java)

        return mapper.readValue(token.body.toString())
    }

}