package com.okestro.kcredit.idp.user.adapter.`in`.web

import com.okestro.kcredit.idp.common.dto.BaseResponse
import com.okestro.kcredit.idp.user.application.port.`in`.usecase.RevertUserPasswordUseCase
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserPasswordRevertController(
    private val revertUserPasswordUseCase: RevertUserPasswordUseCase
) {

    @PatchMapping("/api/users/{userId}/password")
    fun revertUserPassword(@PathVariable("userId") userId: Long) : BaseResponse<Nothing> {
        revertUserPasswordUseCase.revertUserPassword(userId)
        return BaseResponse.ok()
    }
}