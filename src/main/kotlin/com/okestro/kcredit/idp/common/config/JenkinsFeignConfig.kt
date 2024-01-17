package com.okestro.kcredit.idp.common.config

import com.okestro.kcredit.idp.common.properties.JenkinsProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JenkinsFeignConfig {

    @Bean
    fun jenkinsFeignInterceptor(jenkinsProperties: JenkinsProperties) =
        JenkinsAuthRequestInterceptor(jenkinsProperties)
}