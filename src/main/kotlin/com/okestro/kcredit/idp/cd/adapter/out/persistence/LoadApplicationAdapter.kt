package com.okestro.kcredit.idp.cd.adapter.out.persistence

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.okestro.kcredit.idp.cd.application.port.out.LoadApplicationPort
import com.okestro.kcredit.idp.common.annotation.ExternalSystemAdapter
import com.okestro.kcredit.idp.common.properties.ArgoCdProperties
import org.springframework.web.client.RestClient

@ExternalSystemAdapter
class LoadApplicationAdapter (
    private val mapper: ObjectMapper,
    private val argoCdProperties: ArgoCdProperties
) : LoadApplicationPort {
    override fun loadApplication() {
        val restClient = RestClient.builder().baseUrl(argoCdProperties.baseUrl).build()

        val results = restClient
            .get()
            .uri("/api/v1/applications")
            .retrieve()
            .toEntity(String::class.java)

        println("uri = ${results.body}")

        return mapper.readValue(results.body.toString())

    }
}