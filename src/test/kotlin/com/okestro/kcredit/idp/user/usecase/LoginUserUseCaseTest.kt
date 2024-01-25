package com.okestro.kcredit.idp.user.usecase

import com.appmattus.kotlinfixture.kotlinFixture
import com.okestro.kcredit.idp.common.exception.CustomException
import com.okestro.kcredit.idp.common.exception.ErrorCode.*
import com.okestro.kcredit.idp.common.utils.JwtUtil
import com.okestro.kcredit.idp.user.application.port.`in`.model.LoginUserCommand
import com.okestro.kcredit.idp.user.application.port.`in`.usecase.LoadUserUseCase
import com.okestro.kcredit.idp.user.application.service.LoginUserService
import com.okestro.kcredit.idp.user.domain.User
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.security.crypto.password.PasswordEncoder


class LoginUserUseCaseTest : BehaviorSpec({

    isolationMode = IsolationMode.InstancePerTest

    val fixture = kotlinFixture()
    val loadUserUseCase = mockk<LoadUserUseCase>()
    val jwtUtil = mockk<JwtUtil>()
    val passwordCrypto = mockk<PasswordEncoder>()
    val loginUserUseCase = LoginUserService(loadUserUseCase, passwordCrypto, jwtUtil)


    Given("올바른 아이디와 비밀번호로 사용자 로그인을 시도하려는 상황에서") {
        val expectedUser = fixture<User>()
        val loginUserCommand = fixture<LoginUserCommand>()

        every {
            passwordCrypto.matches(loginUserCommand.loginPassword, expectedUser.loginPassword)
        } returns true
        every { loadUserUseCase.loadUserByLoginId(loginUserCommand.loginId) } returns expectedUser
        every { jwtUtil.generateToken(loginUserCommand.loginId, expectedUser.role) } returns "generatedToken"

        When("올바른 아이디와 비밀번호로 로그인을 시도하면") {
            val expectedResult = loginUserUseCase.login(loginUserCommand)

            Then("로그인이 성공하고 토큰이 발급되어야 한다") {
                expectedResult shouldBe "generatedToken"

                verify(exactly = 1) {
                    passwordCrypto.matches(loginUserCommand.loginPassword, expectedUser.loginPassword)
                }
                verify(exactly = 1) { jwtUtil.generateToken(loginUserCommand.loginId, expectedUser.role) }
                verify(exactly = 1) { loadUserUseCase.loadUserByLoginId(loginUserCommand.loginId) }
            }
        }
    }

    Given("비정상적인 아이디와 비밀번호로 로그인을 시도하려는 상황에서") {
        val expectedUser = fixture<User>()
        val loginUserCommand = fixture<LoginUserCommand>()

        every {
            passwordCrypto.matches(loginUserCommand.loginPassword, expectedUser.loginPassword)
        } returns false

        every { loadUserUseCase.loadUserByLoginId(loginUserCommand.loginId) } returns expectedUser

        When("비정상적인 아이디와 비밀번호로 로그인을 시도하면") {
            val exception = shouldThrow<CustomException> {
                loginUserUseCase.login(loginUserCommand)
            }

            Then("예외가 발생해야 한다") {
                exception.errorCode shouldBe LOGIN_INVALID
            }
        }
    }
})