package com.okestro.kcredit.idp.user.adapter.`in`.web

import com.okestro.kcredit.idp.common.dto.BaseResponse
import com.okestro.kcredit.idp.user.application.port.`in`.model.ModifyUserCommand
import com.okestro.kcredit.idp.user.application.port.`in`.usecase.ModifyUserUseCase
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class UserModifyController(
    private val modifyUserUseCase: ModifyUserUseCase
) {

    @PatchMapping("/api/users/{userId}")
    fun modifyUser(@PathVariable("userId") userId: Long,
                   @RequestBody userCommand: ModifyUserCommand) =
        BaseResponse.ok(modifyUserUseCase.modifyUser(userId, userCommand))
}