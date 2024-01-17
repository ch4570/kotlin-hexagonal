package com.okestro.kcredit.idp.gitlab.application.service

import com.okestro.kcredit.idp.gitlab.application.port.`in`.usecase.LoadRepositoryUseCase
import com.okestro.kcredit.idp.gitlab.application.port.out.LoadRepositoryPort
import org.springframework.stereotype.Service

@Service
class LoadRepositoryService (
    private val loadRepositoryPort: LoadRepositoryPort
) : LoadRepositoryUseCase{
    override fun loadRepositoryList() =
        loadRepositoryPort.loadRepositoryList()
}