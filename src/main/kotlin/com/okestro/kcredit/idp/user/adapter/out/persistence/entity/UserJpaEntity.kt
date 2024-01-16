package com.okestro.kcredit.idp.user.adapter.out.persistence.entity


import com.okestro.kcredit.idp.user.domain.Role
import jakarta.persistence.*

@Entity
data class UserJpaEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    val id: Long? = null,

    @Column(name = "LOGIN_ID")
    val loginId: String,

    @Column(name = "LOGIN_PASSWORD")
    val loginPassword: String,

    @Column(name = "USER_NAME")
   val userName: String,

    @Column(name = "DEPARTMENT")
    val department: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE")
    val role: Role
)