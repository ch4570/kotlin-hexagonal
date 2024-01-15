package com.okestro.kcredit.idp.user.application.service

import com.okestro.kcredit.idp.user.application.port.`in`.usecase.LoadUserUseCase
import com.okestro.kcredit.idp.user.application.port.out.LoadUserPort
import com.okestro.kcredit.idp.user.domain.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class LoadUserService(
    private val loadUserPort: LoadUserPort
) : LoadUserUseCase {
    override fun loadUserById(userId: Long) =
        loadUserPort.loadUserById(userId)

    override fun loadAllUsers() =
        loadUserPort.loadAllUsers()

    override fun loadUserByLoginId(loginId: String) =
        loadUserPort.loadUserByLoginId(loginId)

}