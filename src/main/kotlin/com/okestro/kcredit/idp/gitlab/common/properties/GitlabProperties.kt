package com.okestro.kcredit.idp.gitlab.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "gitlab")
class GitlabProperties (
    val url: String,
    val token: String
)