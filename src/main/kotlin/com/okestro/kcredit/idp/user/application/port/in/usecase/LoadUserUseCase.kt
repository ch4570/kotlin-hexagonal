package com.okestro.kcredit.idp.user.application.port.`in`.usecase

import com.okestro.kcredit.idp.common.annotation.UseCase
import com.okestro.kcredit.idp.user.domain.User

@UseCase
interface LoadUserUseCase {

    fun loadUserById(userId: Long): User
    fun loadAllUsers(): List<User>
    fun loadUserByLoginId(loginId: String): User
}