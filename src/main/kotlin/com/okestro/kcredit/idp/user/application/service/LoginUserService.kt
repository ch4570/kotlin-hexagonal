package com.okestro.kcredit.idp.user.application.service

import com.okestro.kcredit.idp.common.exception.CustomException
import com.okestro.kcredit.idp.common.exception.ErrorCode.*
import com.okestro.kcredit.idp.common.utils.JwtUtil
import com.okestro.kcredit.idp.user.application.port.`in`.model.LoginUserCommand
import com.okestro.kcredit.idp.user.application.port.`in`.usecase.LoadUserUseCase
import com.okestro.kcredit.idp.user.application.port.`in`.usecase.LoginUserUseCase
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginUserService(
    private val loadUserUseCase: LoadUserUseCase,
    private val passwordCrypto: PasswordEncoder,
    private val jwtUtil: JwtUtil
) : LoginUserUseCase {


    override fun login(userCommand: LoginUserCommand) : String {
        val user = loadUserUseCase.loadUserByLoginId(userCommand.loginId)


       if (!passwordCrypto.matches(userCommand.loginPassword, user.loginPassword))
           throw CustomException(LOGIN_INVALID)

        return jwtUtil.generateToken(userCommand.loginId, user.role)
    }

}