package com.okestro.kcredit.idp.gitlab.adapter.out.persistence

import com.fasterxml.jackson.databind.ObjectMapper
import com.okestro.kcredit.idp.common.annotation.ExternalSystemAdapter

@ExternalSystemAdapter
class LoadDetailRepoAdapter (
    private val mapper: ObjectMapper
){

    fun loadDetailRepoList(){
//        val toEntity = connectGitLab
//            .get()
//            .uri("/projects/53145568/repository/tree")
//            .header("PRIVATE-TOKEN", gitlabProperties.token)
//            .retrieve()
//            .body(String::class.java)

        //        val gitlabApiResponseList: List<LoadDetailRepoResponse> =
//            mapper.readValue(toEntity.toString())
    }

}