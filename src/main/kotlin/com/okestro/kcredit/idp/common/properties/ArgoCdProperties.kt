package com.okestro.kcredit.idp.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "argocd")
class ArgoCdProperties (
    val id: String,
    val password: String,
    val baseUrl: String
)