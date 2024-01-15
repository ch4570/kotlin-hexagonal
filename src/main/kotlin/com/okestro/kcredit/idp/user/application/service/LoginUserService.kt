package com.okestro.kcredit.idp.user.application.service

import com.okestro.kcredit.idp.common.utils.UserPasswordCrypto
import com.okestro.kcredit.idp.user.application.port.`in`.model.LoginUserCommand
import com.okestro.kcredit.idp.user.application.port.`in`.usecase.LoginUserUseCase
import org.springframework.stereotype.Service

@Service
class LoginUserService(
    private val loadUserService: LoadUserService,
    private val passwordCrypto: UserPasswordCrypto
) : LoginUserUseCase {


    override fun login(userCommand: LoginUserCommand): Boolean {
        val user = loadUserService.loadUserByLoginId(userCommand.loginId)

        return passwordCrypto.checkPassword(userCommand.loginPassword, user.loginPassword)
    }

}