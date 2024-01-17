package com.okestro.kcredit.idp.gitlab.application.port.out.model

data class LoadAllRepoResponse(
    val id: Long?,
    val description: String?,
    val name: String?,
    val nameWithNamespace: String?,
    val path: String?,
    val pathWithNamespace: String?,
    val createdAt: String?,
    val defaultBranch: String?,
    val tagList: List<String>?,
    val topics: List<String>?,
    val sshUrlToRepo: String?,
    val httpUrlToRepo: String?,
    val webUrl: String?,
    val readmeUrl: String?,
    val forksCount: Int?,
    val avatarUrl: String?,
    val starCount: Int?,
    val lastActivityAt: String?,
    val namespace: GitLabNamespace?,
    val containerRegistryImagePrefix: String?,
    val links: GitLabLinks?,
    val codeSuggestions: Boolean?,
    val packagesEnabled: Boolean?,
    val emptyRepo: Boolean?,
    val archived: Boolean?,
    val visibility: String?,
    val owner: GitLabOwner?,
    val resolveOutdatedDiffDiscussions: Boolean?,
    val containerExpirationPolicy: GitLabContainerExpirationPolicy?,
    val issuesEnabled: Boolean?,
    val mergeRequestsEnabled: Boolean?,
    val issuesAccessLevel: String?,
    val repositoryAccessLevel: String?,
    val mergeRequestsAccessLevel: String?,
    val forkingAccessLevel: String?,
    val emailsEnabled: Boolean?,
    val creatorId: Long?,
    val importUrl: String?,
    val importType: String?,
    val importStatus: String?,
    val openIssuesCount: Int?,
    val descriptionHtml: String?,
    val updatedAt: String?,
    val permissions: GitLabPermissions?
)

data class GitLabNamespace(
    val id: Long?,
    val name: String?,
    val path: String?,
    val kind: String?,
    val fullPath: String?,
    val parentId: Long?,
    val avatarUrl: String?,
    val webUrl: String?
) {
    // Default constructor with a default value for fullPath
    constructor(
        id: Long?,
        name: String?,
        path: String?,
        kind: String?,
        parentId: Long?,
        avatarUrl: String?,
        webUrl: String?
    ) : this(id, name, path, kind, "", parentId, avatarUrl, webUrl)
}

data class GitLabLinks(
    val self: String?,
    val issues: String?,
    val mergeRequests: String?,
    val repoBranches: String?,
    val labels: String?,
    val events: String?,
    val members: String?,
    val clusterAgents: String?
)

data class GitLabOwner(
    val id: Long?,
    val username: String?,
    val name: String?,
    val state: String?,
    val locked: Boolean?,
    val avatarUrl: String?,
    val webUrl: String?
)

data class GitLabContainerExpirationPolicy(
    val cadence: String?,
    val enabled: Boolean?,
    val keepN: Int?,
    val olderThan: String?,
    val nameRegex: String?,
    val nameRegexKeep: String?,
    val nextRunAt: String?
)

data class GitLabPermissions(
    val projectAccess: GitLabAccess?,
    val groupAccess: GitLabAccess?
)

data class GitLabAccess(
    val accessLevel: Int?,
    val notificationLevel: Int?
)
