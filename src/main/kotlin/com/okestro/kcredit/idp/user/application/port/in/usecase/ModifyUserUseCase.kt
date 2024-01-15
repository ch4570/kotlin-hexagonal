package com.okestro.kcredit.idp.user.application.port.`in`.usecase

import com.okestro.kcredit.idp.common.annotation.UseCase
import com.okestro.kcredit.idp.user.application.port.`in`.model.ModifyUserCommand
import com.okestro.kcredit.idp.user.domain.User

@UseCase
interface ModifyUserUseCase {

    fun modifyUser(userId: Long, userCommand: ModifyUserCommand): User
}