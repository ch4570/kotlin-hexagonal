package com.okestro.kcredit.idp.ci.adapter.out.jenkins.adapter

import com.okestro.kcredit.idp.ci.application.port.out.ExecuteBuildPort
import com.okestro.kcredit.idp.common.annotation.ExternalSystemAdapter
import com.okestro.kcredit.idp.common.config.JenkinsClient

@ExternalSystemAdapter
class ExecuteBuildExternalAdapter(
    private val jenkinsClient: JenkinsClient
) : ExecuteBuildPort {

    override fun executeBuild(jobName: String) {
        jenkinsClient.executeBuild(jobName)
    }

}