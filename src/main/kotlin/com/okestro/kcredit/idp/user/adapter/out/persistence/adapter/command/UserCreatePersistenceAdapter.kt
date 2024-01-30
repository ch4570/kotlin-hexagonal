package com.okestro.kcredit.idp.user.adapter.out.persistence.adapter.command

import com.okestro.kcredit.idp.ci.common.util.PasswordCrypto
import com.okestro.kcredit.idp.common.annotation.PersistenceAdapter
import com.okestro.kcredit.idp.user.adapter.out.persistence.entity.UserJpaEntity
import com.okestro.kcredit.idp.user.adapter.out.persistence.mapper.UserMapper
import com.okestro.kcredit.idp.user.adapter.out.persistence.repository.UserRepository
import com.okestro.kcredit.idp.user.application.port.`in`.model.RegisterUserCommand
import com.okestro.kcredit.idp.user.application.port.out.RegisterUserPort
import com.okestro.kcredit.idp.user.domain.User
import org.springframework.security.crypto.password.PasswordEncoder

@PersistenceAdapter
class UserCreatePersistenceAdapter(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
) : RegisterUserPort {

    override fun registerUser(userCommand: RegisterUserCommand) : User {
        val userJpaEntity = UserJpaEntity(
            loginId = userCommand.loginId,
            loginPassword = userCommand.loginPassword,
            userName = userCommand.name,
            department = userCommand.department,
            role = userCommand.role
        )

        return userMapper.jpaEntityToDomain(userRepository.save(userJpaEntity))
    }
}