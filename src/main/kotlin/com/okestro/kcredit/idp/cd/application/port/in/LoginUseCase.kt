package com.okestro.kcredit.idp.cd.application.port.`in`

import com.okestro.kcredit.idp.common.annotation.UseCase

@UseCase
interface LoginUseCase {
    fun getArgoCdToken()
}