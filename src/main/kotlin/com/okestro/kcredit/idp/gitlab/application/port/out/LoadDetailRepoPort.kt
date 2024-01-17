package com.okestro.kcredit.idp.gitlab.application.port.out

import com.okestro.kcredit.idp.gitlab.application.port.out.model.LoadDetailRepoResponse

interface LoadDetailRepoPort {
    fun loadDetailRepository(repoId: Int): List<LoadDetailRepoResponse>
}