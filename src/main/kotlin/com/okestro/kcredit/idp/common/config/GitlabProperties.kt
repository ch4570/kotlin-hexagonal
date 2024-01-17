package com.okestro.kcredit.idp.common.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "gitlab")
data class GitlabProperties (
    var url: String,
    var token: String
)