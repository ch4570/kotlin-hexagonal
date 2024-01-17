package com.okestro.kcredit.idp.cd.application.service

import com.okestro.kcredit.idp.cd.application.port.`in`.LoginUseCase
import com.okestro.kcredit.idp.common.properties.ArgoCdProperties
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class LoginService(
    private val argoCdProperties: ArgoCdProperties
): LoginUseCase {
    override fun getArgoCdToken() {
        val restClient = RestClient.builder().baseUrl(argoCdProperties.baseUrl).build()

        val uri = restClient
            .method(HttpMethod.GET)
            .uri("/api/v1/session")
            .body("""{"username":"${argoCdProperties.id}","password":"${argoCdProperties.password}"}""")
            .retrieve()
            .toEntity(String::class.java)

        println("uri = ${uri.body}")
    }

}