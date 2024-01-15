package com.okestro.kcredit.idp.user.application.port.`in`.usecase

import com.okestro.kcredit.idp.common.annotation.UseCase
import com.okestro.kcredit.idp.user.application.port.`in`.model.LoginUserCommand

@UseCase
interface LoginUserUseCase {

    fun login(userCommand: LoginUserCommand): Boolean
}