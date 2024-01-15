package com.okestro.kcredit.idp.user.application.port.out

import com.okestro.kcredit.idp.user.domain.User

interface ModifyUserPort {

    fun modifyUser(userId: Long, user: User) : User
}