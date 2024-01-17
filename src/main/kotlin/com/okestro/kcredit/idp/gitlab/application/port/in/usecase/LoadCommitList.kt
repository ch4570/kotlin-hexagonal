package com.okestro.kcredit.idp.gitlab.application.port.`in`.usecase

import com.okestro.kcredit.idp.common.annotation.UseCase

@UseCase
interface LoadCommitList {

    fun loadCommitList()
}