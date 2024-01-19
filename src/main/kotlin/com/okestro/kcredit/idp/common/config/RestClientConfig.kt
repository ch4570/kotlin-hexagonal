package com.okestro.kcredit.idp.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfig {

    @Bean
    fun restClient() = RestClient.create()
}