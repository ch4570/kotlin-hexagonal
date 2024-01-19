package com.okestro.kcredit.idp.ci.common.util

import java.util.*

abstract class JenkinsClientProvider {

    companion object {
        fun createBasicAuth(userId: String, token: String) =
            "Basic ${Base64.getEncoder()
                .encodeToString("$userId:${token}".toByteArray())}"

    }
}