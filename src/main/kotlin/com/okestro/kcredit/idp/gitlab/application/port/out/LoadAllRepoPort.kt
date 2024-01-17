package com.okestro.kcredit.idp.gitlab.application.port.out

import com.okestro.kcredit.idp.gitlab.application.port.out.model.LoadAllRepoResponse

interface LoadAllRepoPort {

    fun loadAllRepoList(): List<LoadAllRepoResponse>
}