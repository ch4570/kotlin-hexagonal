package com.okestro.kcredit.idp.gitlab.application.port.`in`.usecase

import com.okestro.kcredit.idp.common.annotation.UseCase
import com.okestro.kcredit.idp.gitlab.application.port.out.model.LoadDetailRepoResponse

@UseCase
interface LoadDetailRepoUseCase {
    fun loadDetailRepository(repoId: Int): List<LoadDetailRepoResponse>
}