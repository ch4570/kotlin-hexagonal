package com.okestro.kcredit.idp.common.config

import com.okestro.kcredit.idp.common.properties.JenkinsProperties
import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.http.HttpHeaders
import java.util.*

class JenkinsAuthRequestInterceptor(
    private val jenkinsProperties: JenkinsProperties
) : RequestInterceptor {
    override fun apply(template: RequestTemplate) {
        val credential = "${jenkinsProperties.id}:${jenkinsProperties.token}"
        val auth = "Basic ${Base64.getEncoder().encodeToString(credential.toByteArray())}"

        template.header(HttpHeaders.AUTHORIZATION, auth)
    }
}