package com.okestro.kcredit.idp.ci.application.port.`in`.usecase

import com.okestro.kcredit.idp.ci.application.port.out.model.Hudson
import com.okestro.kcredit.idp.common.annotation.UseCase

@UseCase
interface LoadPipelineUseCase {

    fun loadPipeLineList(): Hudson
}