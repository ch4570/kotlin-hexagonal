package com.okestro.kcredit.idp.ci.application.service

import com.okestro.kcredit.idp.ci.application.port.`in`.usecase.ExecuteBuildUseCase
import com.okestro.kcredit.idp.ci.application.port.out.ExecuteBuildPort
import org.springframework.stereotype.Service

@Service
class ExecuteBuildService(
    private val executeBuildPort: ExecuteBuildPort
) : ExecuteBuildUseCase {

    override fun executeBuildUseCase(jobName: String) {
        executeBuildPort.executeBuild(jobName)
    }

}