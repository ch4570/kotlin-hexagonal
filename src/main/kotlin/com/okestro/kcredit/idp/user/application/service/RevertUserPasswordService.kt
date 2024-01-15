package com.okestro.kcredit.idp.user.application.service

import com.okestro.kcredit.idp.common.utils.UserPasswordCrypto
import com.okestro.kcredit.idp.user.application.port.`in`.usecase.RevertUserPasswordUseCase
import com.okestro.kcredit.idp.user.application.port.out.RevertUserPasswordPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class RevertUserPasswordService(
    private val loadUserService: LoadUserService,
    private val revertUserPasswordPort: RevertUserPasswordPort,
    private val passwordCrypto: UserPasswordCrypto
) : RevertUserPasswordUseCase {

    override fun revertUserPassword(userId: Long) {
        val user = loadUserService.loadUserById(userId)
        user.revertPassword(passwordCrypto.encryptPassword("1234"))
        revertUserPasswordPort.revertUserPassword(userId, user)
    }

}