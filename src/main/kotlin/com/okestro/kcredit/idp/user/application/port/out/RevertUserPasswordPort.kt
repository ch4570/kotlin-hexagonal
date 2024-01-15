package com.okestro.kcredit.idp.user.application.port.out

import com.okestro.kcredit.idp.user.domain.User

interface RevertUserPasswordPort {

    fun revertUserPassword(userId: Long, user: User)
}