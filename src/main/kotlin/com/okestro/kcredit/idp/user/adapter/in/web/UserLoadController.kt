package com.okestro.kcredit.idp.user.adapter.`in`.web

import com.okestro.kcredit.idp.common.dto.BaseResponse
import com.okestro.kcredit.idp.user.application.port.`in`.usecase.LoadUserUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserLoadController(
    val loadUserUseCase: LoadUserUseCase
) {

    @GetMapping("/api/users/{userId}")
    fun loadUserById(@PathVariable("userId") userId: Long) =
        BaseResponse.ok(loadUserUseCase.loadUserById(userId))

    @GetMapping("/api/users")
    fun loadAllUsers() =
        BaseResponse.ok(loadUserUseCase.loadAllUsers())
}