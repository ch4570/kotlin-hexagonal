package com.okestro.kcredit.idp.user.application.service

import com.okestro.kcredit.idp.common.exception.CustomException
import com.okestro.kcredit.idp.common.exception.ErrorCode.*
import com.okestro.kcredit.idp.common.utils.JwtUtil
import com.okestro.kcredit.idp.common.utils.UserPasswordCrypto
import com.okestro.kcredit.idp.user.application.port.`in`.model.LoginUserCommand
import com.okestro.kcredit.idp.user.application.port.`in`.usecase.LoadUserUseCase
import com.okestro.kcredit.idp.user.application.port.`in`.usecase.LoginUserUseCase
import org.springframework.stereotype.Service

@Service
class LoginUserService(
    private val loadUserUseCase: LoadUserUseCase,
    private val passwordCrypto: UserPasswordCrypto,
    private val jwtUtil: JwtUtil
) : LoginUserUseCase {


    override fun login(userCommand: LoginUserCommand) : String {
        val user = loadUserUseCase.loadUserByLoginId(userCommand.loginId)

       if (!passwordCrypto.checkPassword(userCommand.loginPassword, user.loginPassword))
           throw CustomException(LOGIN_INVALID)

        return jwtUtil.generateToken(userCommand.loginId, user.role)
    }

}