package com.okestro.kcredit.idp.cd.application.port.`in`.usecase

import com.okestro.kcredit.idp.common.annotation.UseCase

@UseCase
interface LoadApplicationUseCase {
    fun loadAllApplications()
}