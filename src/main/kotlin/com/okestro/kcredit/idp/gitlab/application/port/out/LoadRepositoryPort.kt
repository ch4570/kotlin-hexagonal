package com.okestro.kcredit.idp.gitlab.application.port.out

import com.okestro.kcredit.idp.gitlab.application.port.out.model.LoadDetailRepoResponse
import com.okestro.kcredit.idp.gitlab.application.port.out.model.LoadRepositoryResponse

interface LoadRepositoryPort {

    fun loadRepositoryList(): LoadRepositoryResponse?
}