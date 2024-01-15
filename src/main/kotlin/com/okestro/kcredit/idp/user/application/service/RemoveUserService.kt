package com.okestro.kcredit.idp.user.application.service

import com.okestro.kcredit.idp.user.application.port.`in`.usecase.RemoveUserUseCase
import com.okestro.kcredit.idp.user.application.port.out.RemoveUserPort
import org.springframework.stereotype.Service

@Service
class RemoveUserService(
    private val removeUserPort: RemoveUserPort
) : RemoveUserUseCase {

    override fun removeUserById(userId: Long) {
        removeUserPort.removeUserById(userId)
    }

}