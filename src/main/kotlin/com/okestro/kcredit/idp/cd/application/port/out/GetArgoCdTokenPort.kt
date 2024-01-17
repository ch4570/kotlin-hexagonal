package com.okestro.kcredit.idp.cd.application.port.out

import com.okestro.kcredit.idp.cd.application.port.out.model.GetArgoCdTokenResponse

interface GetArgoCdTokenPort {

    fun getArgoCdToken(): GetArgoCdTokenResponse
}