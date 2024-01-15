package com.okestro.kcredit.idp.user.adapter.out.persistence.entity

import com.okestro.kcredit.idp.user.application.port.`in`.model.ModifyUserCommand
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

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
    val department: String
) {

    fun updateUser() {

    }
}