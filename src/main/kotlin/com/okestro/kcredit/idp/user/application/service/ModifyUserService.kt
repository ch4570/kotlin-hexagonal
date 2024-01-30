package com.okestro.kcredit.idp.user.application.service

import com.okestro.kcredit.idp.ci.common.util.PasswordCrypto
import com.okestro.kcredit.idp.user.application.port.`in`.model.ModifyUserCommand
import com.okestro.kcredit.idp.user.application.port.`in`.usecase.LoadUserUseCase
import com.okestro.kcredit.idp.user.application.port.`in`.usecase.ModifyUserUseCase
import com.okestro.kcredit.idp.user.application.port.out.ModifyUserPort
import com.okestro.kcredit.idp.user.domain.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ModifyUserService(
    private val loadUserUseCase: LoadUserUseCase,
    private val modifyUserPort: ModifyUserPort,
    private val passwordCrypto: PasswordCrypto
) : ModifyUserUseCase {

    override fun modifyUser(userId: Long, userCommand: ModifyUserCommand) : User {
        val user = loadUserUseCase.loadUserById(userId)

        user.updateUser(
            loginPassword = passwordCrypto.encodePassword(userCommand.loginPassword),
            name = userCommand.name,
            department = userCommand.department
        )

        return modifyUserPort.modifyUser(userId, user)
    }
}