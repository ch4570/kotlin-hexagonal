package com.okestro.kcredit.idp.user.application.service

import com.okestro.kcredit.idp.common.utils.UserPasswordCrypto
import com.okestro.kcredit.idp.user.application.port.`in`.model.ModifyUserCommand
import com.okestro.kcredit.idp.user.application.port.`in`.usecase.ModifyUserUseCase
import com.okestro.kcredit.idp.user.application.port.out.ModifyUserPort
import com.okestro.kcredit.idp.user.domain.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ModifyUserService(
    private val loadUserService: LoadUserService,
    private val modifyUserPort: ModifyUserPort,
    private val passwordCrypto: UserPasswordCrypto
) : ModifyUserUseCase {

    override fun modifyUser(userId: Long, userCommand: ModifyUserCommand): User {
        val user = loadUserService.loadUserById(userId)
        user.updateUser(
            loginPassword = passwordCrypto.encryptPassword(userCommand.loginPassword),
            name = userCommand.name,
            department = userCommand.department
        )

        return modifyUserPort.modifyUser(userId, user)
    }
}