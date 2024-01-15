package com.okestro.kcredit.idp.user.application.port.`in`.usecase

import com.okestro.kcredit.idp.common.annotation.UseCase
import com.okestro.kcredit.idp.user.application.port.`in`.model.RegisterUserCommand
import com.okestro.kcredit.idp.user.domain.User

@UseCase
interface RegisterUserUseCase {

    fun registerUser(userCommand: RegisterUserCommand): User
}