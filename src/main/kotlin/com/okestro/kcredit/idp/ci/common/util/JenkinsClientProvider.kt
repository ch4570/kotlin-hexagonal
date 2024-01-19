package com.okestro.kcredit.idp.ci.common.util

import org.springframework.http.HttpHeaders
import java.util.*

abstract class JenkinsClientProvider {

    companion object {
        fun createJenkinsAuthHeader(userId: String, token: String) : HttpHeaders {
            val credential = "Basic ${Base64.getEncoder()
                .encodeToString("$userId:${token}".toByteArray())}"
            val authHeader = HttpHeaders()

            authHeader.set(HttpHeaders.AUTHORIZATION, credential)
            return authHeader
        }

    }
}