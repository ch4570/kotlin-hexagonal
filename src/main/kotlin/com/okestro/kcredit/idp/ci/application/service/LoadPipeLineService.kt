package com.okestro.kcredit.idp.ci.application.service

import com.okestro.kcredit.idp.ci.application.port.`in`.usecase.LoadPipelineUseCase
import com.okestro.kcredit.idp.ci.application.port.out.LoadPipeLinePort
import org.springframework.stereotype.Service

@Service
class LoadPipeLineService(
    private val loadPipeLinePort: LoadPipeLinePort
) : LoadPipelineUseCase {
    override fun loadPipeLineList() =
        loadPipeLinePort.loadPipeLineList()
}