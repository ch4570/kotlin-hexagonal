package com.okestro.kcredit.idp.gitlab.common.config

import com.okestro.kcredit.idp.gitlab.common.properties.GitlabProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GitlabFeignConfig {

    @Bean
    fun gitlabFeignInterceptor(gitlabProperties: GitlabProperties) =
        GitlabAuthRequestInterceptor(gitlabProperties)
}