package com.okestro.kcredit.idp.common.utils

import com.okestro.kcredit.idp.user.domain.User
import org.springframework.security.core.context.SecurityContextHolder

abstract class SecurityUtil {

    companion object {
        fun getUserLoginId() : String {
            val user = SecurityContextHolder.getContext()
                .authentication
                .principal as User

            return user.loginId
        }
    }
}