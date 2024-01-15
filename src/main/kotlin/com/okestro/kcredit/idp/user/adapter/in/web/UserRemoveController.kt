package com.okestro.kcredit.idp.user.adapter.`in`.web

import com.okestro.kcredit.idp.common.dto.BaseResponse
import com.okestro.kcredit.idp.user.application.port.`in`.usecase.RemoveUserUseCase
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserRemoveController(
    private val removeUserUseCase: RemoveUserUseCase
) {

    @DeleteMapping("/api/users/{userId}")
    fun removeUser(@PathVariable("userId") userId: Long) : BaseResponse<Nothing> {
        removeUserUseCase.removeUserById(userId)
        return BaseResponse.ok()
    }

}