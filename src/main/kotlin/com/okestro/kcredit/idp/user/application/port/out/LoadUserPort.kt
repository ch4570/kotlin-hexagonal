package com.okestro.kcredit.idp.user.application.port.out

import com.okestro.kcredit.idp.user.domain.User

interface LoadUserPort {

    fun loadUserById(userId: Long): User
    fun loadAllUsers(): List<User>
    fun loadUserByLoginId(loginId: String): User
}