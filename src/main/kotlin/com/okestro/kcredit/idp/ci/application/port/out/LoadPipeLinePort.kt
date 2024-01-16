package com.okestro.kcredit.idp.ci.application.port.out

import com.okestro.kcredit.idp.ci.application.port.out.model.Hudson

interface LoadPipeLinePort {

    fun loadPipeLineList(): Hudson
}