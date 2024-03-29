package com.okestro.kcredit.idp.ci.adapter.out.jenkins.adapter

import com.okestro.kcredit.idp.ci.application.port.out.model.Hudson
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@FeignClient(name = "jenkinsClient", url = "\${jenkins.baseUrl}")
interface FeignClient {

    @GetMapping("/api/json")
    fun loadPipeLineList(): Hudson?

    @PostMapping("/job/{jobName}/build")
    fun executeBuild(@PathVariable("jobName") jobName: String)
}