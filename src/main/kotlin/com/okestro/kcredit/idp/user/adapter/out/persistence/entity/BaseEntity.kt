package com.okestro.kcredit.idp.user.adapter.out.persistence.entity

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity(

    @CreatedDate
    val regDate: LocalDateTime,

    @CreatedBy
    val regId: String,

    @LastModifiedDate
    val modDate: LocalDateTime,

    @LastModifiedBy
    val modId: String,
)