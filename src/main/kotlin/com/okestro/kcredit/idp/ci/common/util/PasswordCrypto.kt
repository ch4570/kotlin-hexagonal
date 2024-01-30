package com.okestro.kcredit.idp.ci.common.util

import com.okestro.kcredit.idp.common.annotation.Util
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder


@Util
class PasswordCrypto(
    private val passwordEncoder: PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
) {
    fun encodePassword(password: String) = passwordEncoder.encode(password)
    fun matchingPassword(plainPassword: String, encodedPassword: String) =
            passwordEncoder.matches(plainPassword, encodedPassword)

}