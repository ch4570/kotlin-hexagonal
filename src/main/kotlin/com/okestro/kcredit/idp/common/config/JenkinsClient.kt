package com.okestro.kcredit.idp.common.config

import com.okestro.kcredit.idp.ci.application.port.out.model.Hudson
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(name= "jenkinsClient", url = "\${jenkins.baseUrl}", configuration = [JenkinsFeignConfig::class])
interface JenkinsClient {

    @GetMapping(value = ["/api/json"])
    fun loadPipeLineList(): Hudson?

    @PostMapping(value = ["/job/{jobName}/build"])
    fun executeBuild(@PathVariable("jobName") jobName: String)

}