package com.okestro.kcredit.idp.ci.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jenkins")
class JenkinsProperties(
    val baseUrl: String,
    val token: String,
    val id: String
)