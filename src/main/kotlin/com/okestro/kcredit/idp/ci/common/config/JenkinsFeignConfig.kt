package com.okestro.kcredit.idp.ci.common.config

import com.okestro.kcredit.idp.ci.common.properties.JenkinsProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JenkinsFeignConfig {

    @Bean
    fun jenkinsFeignInterceptor(jenkinsProperties: JenkinsProperties) =
        JenkinsAuthRequestInterceptor(jenkinsProperties)
}