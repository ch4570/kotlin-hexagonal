package com.okestro.kcredit.idp.user.adapter.out.persistence.adapter.command

import com.okestro.kcredit.idp.common.annotation.PersistenceAdapter
import com.okestro.kcredit.idp.user.adapter.out.persistence.mapper.UserMapper
import com.okestro.kcredit.idp.user.adapter.out.persistence.repository.UserRepository
import com.okestro.kcredit.idp.user.application.port.out.RevertUserPasswordPort
import com.okestro.kcredit.idp.user.domain.User

@PersistenceAdapter
class UserRevertPasswordPersistenceAdapter(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper,
) : RevertUserPasswordPort {

    override fun revertUserPassword(userId: Long, user: User) {
        userRepository.save(userMapper.domainToJpaEntity(userId, user))
    }

}