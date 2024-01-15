package com.okestro.kcredit.idp.user.adapter.out.persistence.mapper

import com.okestro.kcredit.idp.common.annotation.Mapper
import com.okestro.kcredit.idp.user.adapter.out.persistence.entity.UserJpaEntity
import com.okestro.kcredit.idp.user.domain.User

@Mapper
class UserMapper {

    fun jpaEntityToDomain(userJpaEntity: UserJpaEntity) =
        User(
            loginId = userJpaEntity.loginId,
            loginPassword = userJpaEntity.loginPassword,
            name = userJpaEntity.userName,
            department = userJpaEntity.department,
            group = mutableListOf(""),
            role = mutableListOf("")
        )

    fun domainToJpaEntity(userId: Long, user: User) =
        UserJpaEntity(
            id = userId,
            loginId = user.loginId,
            loginPassword = user.loginPassword,
            userName = user.name,
            department = user.department
        )

}