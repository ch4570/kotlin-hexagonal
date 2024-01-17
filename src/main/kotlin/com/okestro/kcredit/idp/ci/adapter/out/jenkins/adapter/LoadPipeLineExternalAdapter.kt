package com.okestro.kcredit.idp.ci.adapter.out.jenkins.adapter

import com.okestro.kcredit.idp.ci.application.port.out.LoadPipeLinePort
import com.okestro.kcredit.idp.common.annotation.ExternalSystemAdapter
import com.okestro.kcredit.idp.common.config.JenkinsClient
import com.okestro.kcredit.idp.common.exception.CustomException
import com.okestro.kcredit.idp.common.exception.ErrorCode.*


@ExternalSystemAdapter
class LoadPipeLineExternalAdapter(
    private val jenkinsClient: JenkinsClient
) : LoadPipeLinePort {

    override fun loadPipeLineList() =
        jenkinsClient.loadPipeLineList() ?: throw CustomException(JENKINS_NO_DATA)
}