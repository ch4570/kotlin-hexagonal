package com.okestro.kcredit.idp.gitlab.application.port.out.model

data class LoadRepositoryResponse(
    val id: Long,
    val description: String?,
    val name: String,
    val nameWithNamespace: String,
    val path: String,
    val pathWithNamespace: String,
    val createdAt: String,
    val defaultBranch: String,
    val tagList: List<String>,
    val topics: List<String>,
    val sshUrlToRepo: String,
    val httpUrlToRepo: String,
    val webUrl: String,
    val readmeUrl: String,
    val forksCount: Int,
    val avatarUrl: String?,
    val starCount: Int,
    val lastActivityAt: String,
    val namespace: GitlabNamespace,
    val containerRegistryImagePrefix: String,
    val links: GitlabLinks,
    val codeSuggestions: Boolean,
    val packagesEnabled: Boolean,
    val emptyRepo: Boolean,
    val archived: Boolean,
    val visibility: String,
    val resolveOutdatedDiffDiscussions: Boolean,
    val containerExpirationPolicy: GitlabContainerExpirationPolicy,
    val issuesEnabled: Boolean,
    val mergeRequestsEnabled: Boolean,
    // ... (다른 필드들을 필요에 따라 추가)
)

data class GitlabNamespace(
    val id: Long,
    val name: String,
    val path: String,
    val kind: String,
    val fullPath: String,
    val parentId: Long?,
    val avatarUrl: String?,
    val webUrl: String
)

data class GitlabLinks(
    val self: String,
    val issues: String,
    val mergeRequests: String,
    val repoBranches: String,
    val labels: String,
    val events: String,
    val members: String,
    val clusterAgents: String
    // ... (다른 링크들을 필요에 따라 추가)
)

data class GitlabContainerExpirationPolicy(
    val cadence: String,
    val enabled: Boolean,
    val keepN: Int,
    val olderThan: String,
    val nameRegex: String,
    val nameRegexKeep: String?,
    val nextRunAt: String
    // ... (다른 정책들을 필요에 따라 추가)
)
