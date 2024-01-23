package com.okestro.kcredit.idp.user.adapter.out.persistence.adapter.query

import com.okestro.kcredit.idp.common.annotation.PersistenceAdapter
import com.okestro.kcredit.idp.common.exception.CustomException
import com.okestro.kcredit.idp.common.exception.ErrorCode.*
import com.okestro.kcredit.idp.user.adapter.out.persistence.mapper.UserMapper
import com.okestro.kcredit.idp.user.adapter.out.persistence.repository.UserRepository
import com.okestro.kcredit.idp.user.application.port.out.LoadUserPort

@PersistenceAdapter
class UserQueryPersistenceAdapter(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : LoadUserPort {

    override fun loadUserById(userId: Long) =
        userMapper.jpaEntityToDomain(
            userRepository.findById(userId)
                .orElseThrow { throw CustomException(USER_NOT_FOUND) })

    override fun loadAllUsers() =
        userRepository.findAll()
            .map { userMapper.jpaEntityToDomain(it) }

    override fun loadUserByLoginId(loginId: String) =
        userMapper.jpaEntityToDomain(userRepository.findByLoginId(loginId)
            .orElseThrow { throw CustomException(USER_NOT_FOUND) })
}