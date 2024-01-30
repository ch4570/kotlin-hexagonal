package com.okestro.kcredit.idp.user.application.service

import com.okestro.kcredit.idp.ci.common.util.PasswordCrypto
import com.okestro.kcredit.idp.user.application.port.`in`.model.RegisterUserCommand
import com.okestro.kcredit.idp.user.application.port.`in`.usecase.RegisterUserUseCase
import com.okestro.kcredit.idp.user.application.port.out.RegisterUserPort
import com.okestro.kcredit.idp.user.domain.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RegisterUserService(
    private val registerUserPort: RegisterUserPort,
    private val passwordCrypto: PasswordCrypto,
) : RegisterUserUseCase {
    override fun registerUser(userCommand: RegisterUserCommand): User {
        val encryptedUserCommand = RegisterUserCommand(
            loginId = userCommand.loginId,
            loginPassword = passwordCrypto.encodePassword(userCommand.loginPassword),
            name = userCommand.name,
            department = userCommand.department,
            role = userCommand.role
        )

        return registerUserPort.registerUser(encryptedUserCommand)
    }
}