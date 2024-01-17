package com.okestro.kcredit.idp.cd.application.service

import com.okestro.kcredit.idp.cd.application.port.`in`.LoadApplicationUseCase
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class LoadApplicationService(
    private val restClient: RestClient
): LoadApplicationUseCase {
    override fun loadAllApplications() {

        val uri = restClient
            .method(HttpMethod.GET)
            .uri("/api/v1/applications")
            .retrieve()
            .toEntity(String::class.java)

        println("uri = ${uri.body}")

    }
}
