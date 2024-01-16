package com.okestro.kcredit.idp.user.adapter.out.persistence.adapter.command

import com.okestro.kcredit.idp.common.annotation.PersistenceAdapter
import com.okestro.kcredit.idp.common.exception.CustomException
import com.okestro.kcredit.idp.common.exception.ErrorCode.*
import com.okestro.kcredit.idp.common.utils.UserPasswordCrypto
import com.okestro.kcredit.idp.user.adapter.out.persistence.entity.UserJpaEntity
import com.okestro.kcredit.idp.user.adapter.out.persistence.mapper.UserMapper
import com.okestro.kcredit.idp.user.adapter.out.persistence.repository.UserRepository
import com.okestro.kcredit.idp.user.application.port.`in`.model.RegisterUserCommand
import com.okestro.kcredit.idp.user.application.port.out.ModifyUserPort
import com.okestro.kcredit.idp.user.application.port.out.RegisterUserPort
import com.okestro.kcredit.idp.user.application.port.out.RemoveUserPort
import com.okestro.kcredit.idp.user.application.port.out.RevertUserPasswordPort
import com.okestro.kcredit.idp.user.domain.Role
import com.okestro.kcredit.idp.user.domain.User

@PersistenceAdapter
class UserCommandPersistenceAdapter(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
    private val passwordCrypto: UserPasswordCrypto
) : RegisterUserPort, ModifyUserPort,
    RemoveUserPort, RevertUserPasswordPort {

    override fun registerUser(userCommand: RegisterUserCommand) : User {
        val userJpaEntity = UserJpaEntity(
            loginId = userCommand.loginId,
            loginPassword = passwordCrypto.encryptPassword(userCommand.loginPassword),
            userName = userCommand.name,
            department = userCommand.department,
            role = userCommand.role
        )

        return userMapper.jpaEntityToDomain(userRepository.save(userJpaEntity))
    }


    override fun modifyUser(userId: Long, user: User): User {
        val userJpaEntity = userMapper.domainToJpaEntity(userId, user)
        return userMapper.jpaEntityToDomain(userRepository.save(userJpaEntity))
    }

    override fun removeUserById(userId: Long) {
        val userJpaEntity = userRepository.findById(userId)
            .orElseThrow { throw CustomException(USER_NOT_FOUND) }

        userRepository.delete(userJpaEntity)
    }

    override fun revertUserPassword(userId: Long, user: User) {
        userRepository.save(userMapper.domainToJpaEntity(userId, user))
    }
}