package com.okestro.kcredit.idp.user.application.service

import com.okestro.kcredit.idp.common.utils.UserPasswordCrypto
import com.okestro.kcredit.idp.user.adapter.out.persistence.entity.UserJpaEntity
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
    private val userPasswordCrypto: UserPasswordCrypto
) : RegisterUserUseCase {
    override fun registerUser(userCommand: RegisterUserCommand): User {
        val userJpaEntity = UserJpaEntity(
            loginId = userCommand.loginId,
            loginPassword = userPasswordCrypto.encryptPassword(userCommand.loginPassword),
            userName = userCommand.name,
            department = userCommand.department
        )

        return registerUserPort.registerUser(userJpaEntity)
    }
}