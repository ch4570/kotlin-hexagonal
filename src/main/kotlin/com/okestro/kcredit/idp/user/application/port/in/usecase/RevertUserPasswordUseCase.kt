package com.okestro.kcredit.idp.user.application.port.`in`.usecase

import com.okestro.kcredit.idp.common.annotation.UseCase

@UseCase
interface RevertUserPasswordUseCase {

    fun revertUserPassword(userId: Long)
}