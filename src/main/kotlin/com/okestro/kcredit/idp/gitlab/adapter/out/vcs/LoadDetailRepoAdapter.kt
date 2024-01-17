package com.okestro.kcredit.idp.gitlab.adapter.out.vcs

import com.okestro.kcredit.idp.common.annotation.ExternalSystemAdapter
import com.okestro.kcredit.idp.gitlab.adapter.out.vcs.client.GitlabClient
import com.okestro.kcredit.idp.gitlab.application.port.out.LoadDetailRepoPort

@ExternalSystemAdapter
class LoadDetailRepoAdapter (
    private val gitlabClient: GitlabClient
): LoadDetailRepoPort{

    override fun loadDetailRepository(repoId: Int) =
        gitlabClient.loadGitlabRepoDetail(repoId)

}