package com.okestro.kcredit.idp.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfig(
    val gitlabProperties: GitlabProperties
){

    @Bean
    fun connectGitLab(): RestClient {
        val restClient: RestClient =
            RestClient.builder()
                .baseUrl(gitlabProperties.url)
                .build()

        return restClient
    }
}