package com.okestro.kcredit.idp.user.domain


class User(
    val loginId: String,
    var loginPassword: String,
    var name: String,
    var department: String,
    val group: MutableList<String>,
    val role: MutableList<String>
) {

    fun updateUser(loginPassword: String, name: String, department: String) {
        this.loginPassword = loginPassword
        this.name = name
        this.department = department
    }

    fun revertPassword(loginPassword: String) {
        this.loginPassword = loginPassword
    }
}