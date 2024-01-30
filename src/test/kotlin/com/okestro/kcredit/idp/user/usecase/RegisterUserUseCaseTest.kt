package com.okestro.kcredit.idp.user.usecase

import com.appmattus.kotlinfixture.kotlinFixture
import com.okestro.kcredit.idp.ci.common.util.PasswordCrypto
import com.okestro.kcredit.idp.user.application.port.`in`.model.RegisterUserCommand
import com.okestro.kcredit.idp.user.application.port.out.RegisterUserPort
import com.okestro.kcredit.idp.user.application.service.RegisterUserService
import com.okestro.kcredit.idp.user.domain.Role.*
import com.okestro.kcredit.idp.user.domain.User
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify


class RegisterUserUseCaseTest : BehaviorSpec({

    val registerUserPort = mockk<RegisterUserPort>()
    val passwordCrypto = mockk<PasswordCrypto>()
    val registerUserUseCase = RegisterUserService(registerUserPort, passwordCrypto)
    val fixture = kotlinFixture()

    Given("회원이 회원 가입하려는 상황에서") {

        val registerUserCommand = fixture<RegisterUserCommand>()
        every {
            passwordCrypto.encodePassword(registerUserCommand.loginPassword)
        } returns "encryptedPassword"

        val expectedUser = User(
            loginId = registerUserCommand.loginId,
            loginPassword = "encryptedPassword",
            name = registerUserCommand.name,
            department = registerUserCommand.department,
            role = registerUserCommand.role
        )


        every { registerUserPort.registerUser(any()) } returns expectedUser

        When("회원 가입에 필요한 정보를 전송하면") {
            val expectedResult = registerUserUseCase.registerUser(registerUserCommand)

            Then("회원 가입이 성공해야 한다") {
                expectedResult shouldBe expectedUser
                verify(exactly = 1) { registerUserPort.registerUser(any()) }
            }
        }
    }
})