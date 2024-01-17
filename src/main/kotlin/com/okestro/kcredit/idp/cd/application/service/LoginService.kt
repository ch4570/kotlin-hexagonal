package com.okestro.kcredit.idp.cd.application.service

import com.okestro.kcredit.idp.cd.application.port.`in`.usecase.GetArgoCdTokenUseCase
import com.okestro.kcredit.idp.cd.application.port.out.GetArgoCdTokenPort
import com.okestro.kcredit.idp.cd.application.port.out.model.GetArgoCdTokenResponse
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val getArgoCdTokenPort: GetArgoCdTokenPort
): GetArgoCdTokenUseCase {
    override fun getArgoCdToken(): GetArgoCdTokenResponse =
        getArgoCdTokenPort.getArgoCdToken()

}