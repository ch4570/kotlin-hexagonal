package com.okestro.kcredit.idp.user.application.port.out

import com.okestro.kcredit.idp.user.adapter.out.persistence.entity.UserJpaEntity
import com.okestro.kcredit.idp.user.application.port.`in`.model.RegisterUserCommand
import com.okestro.kcredit.idp.user.domain.User


interface RegisterUserPort {

    fun registerUser(userCommand: RegisterUserCommand): User
}