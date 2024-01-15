package com.okestro.kcredit.idp.user.adapter.out.persistence.repository

import com.okestro.kcredit.idp.user.adapter.out.persistence.entity.UserJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<UserJpaEntity, Long> {
    fun findByLoginId(loginId: String) : Optional<UserJpaEntity>
}