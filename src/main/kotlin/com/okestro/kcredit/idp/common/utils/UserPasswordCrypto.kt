package com.okestro.kcredit.idp.common.utils

import com.okestro.kcredit.idp.common.annotation.Util
import org.springframework.security.crypto.password.PasswordEncoder

@Util
class UserPasswordCrypto(
    private val passwordEncoder: PasswordEncoder
) {

    fun encryptPassword(plainText: String) =
        passwordEncoder.encode(plainText)

    fun checkPassword(plainPassword: String, encryptPassword: String) =
        passwordEncoder.matches(plainPassword, encryptPassword)

}