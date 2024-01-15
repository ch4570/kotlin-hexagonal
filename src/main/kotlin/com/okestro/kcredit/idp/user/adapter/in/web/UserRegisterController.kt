package com.okestro.kcredit.idp.user.adapter.`in`.web

import com.okestro.kcredit.idp.common.dto.BaseResponse
import com.okestro.kcredit.idp.user.application.port.`in`.model.RegisterUserCommand
import com.okestro.kcredit.idp.user.application.port.`in`.usecase.RegisterUserUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserRegisterController(
    private val registerUserUseCase: RegisterUserUseCase
) {

    @PostMapping("/api/users")
    fun registerUser(@RequestBody userCommand: RegisterUserCommand) =
        BaseResponse.ok(registerUserUseCase.registerUser(userCommand))
}