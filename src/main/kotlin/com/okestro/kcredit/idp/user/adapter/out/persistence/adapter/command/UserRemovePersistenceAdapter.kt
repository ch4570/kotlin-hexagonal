package com.okestro.kcredit.idp.user.adapter.out.persistence.adapter.command

import com.okestro.kcredit.idp.common.annotation.PersistenceAdapter
import com.okestro.kcredit.idp.common.exception.CustomException
import com.okestro.kcredit.idp.common.exception.ErrorCode
import com.okestro.kcredit.idp.user.adapter.out.persistence.repository.UserRepository
import com.okestro.kcredit.idp.user.application.port.out.RemoveUserPort

@PersistenceAdapter
class UserRemovePersistenceAdapter(
    private val userRepository: UserRepository,
) : RemoveUserPort {

    override fun removeUserById(userId: Long) {
        val userJpaEntity = userRepository.findById(userId)
            .orElseThrow { throw CustomException(ErrorCode.USER_NOT_FOUND) }

        userRepository.delete(userJpaEntity)
    }
}