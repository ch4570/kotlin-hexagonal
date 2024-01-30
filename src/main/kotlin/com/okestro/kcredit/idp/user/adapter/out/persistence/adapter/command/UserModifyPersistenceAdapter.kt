package com.okestro.kcredit.idp.user.adapter.out.persistence.adapter.command

import com.okestro.kcredit.idp.common.annotation.PersistenceAdapter
import com.okestro.kcredit.idp.user.adapter.out.persistence.mapper.UserMapper
import com.okestro.kcredit.idp.user.adapter.out.persistence.repository.UserRepository
import com.okestro.kcredit.idp.user.application.port.out.ModifyUserPort
import com.okestro.kcredit.idp.user.domain.User

@PersistenceAdapter
class UserModifyPersistenceAdapter(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
) : ModifyUserPort{

    override fun modifyUser(userId: Long, user: User): User {
        val userJpaEntity = userMapper.domainToJpaEntity(userId, user)
        return userMapper.jpaEntityToDomain(userRepository.save(userJpaEntity))
    }
}