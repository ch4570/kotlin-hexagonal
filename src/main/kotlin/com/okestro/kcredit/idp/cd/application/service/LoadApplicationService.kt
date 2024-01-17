package com.okestro.kcredit.idp.cd.application.service

import com.okestro.kcredit.idp.cd.application.port.`in`.usecase.LoadApplicationUseCase
import com.okestro.kcredit.idp.cd.application.port.out.LoadApplicationPort
import org.springframework.stereotype.Service

@Service
class LoadApplicationService(
    private val loadApplicationPort: LoadApplicationPort
): LoadApplicationUseCase {
    override fun loadAllApplications() =
        loadApplicationPort.loadApplication()
}
