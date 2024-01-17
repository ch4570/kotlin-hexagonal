package com.okestro.kcredit.idp.gitlab.common.config

import com.okestro.kcredit.idp.gitlab.common.properties.GitlabProperties
import feign.RequestInterceptor
import feign.RequestTemplate

class GitlabAuthRequestInterceptor (
    private val gitlabProperties: GitlabProperties
): RequestInterceptor{
    override fun apply(template: RequestTemplate) {
        template.header("PRIVATE-TOKEN", gitlabProperties.token)
    }


}