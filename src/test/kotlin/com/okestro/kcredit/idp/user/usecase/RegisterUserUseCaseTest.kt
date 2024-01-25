package com.okestro.kcredit.idp.user.usecase

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
    val registerUserUseCase = RegisterUserService(registerUserPort)

    Given("회원이 회원 가입하려는 상황에서") {
        val userCommand = RegisterUserCommand(
            loginId = "OkestroUser",
            loginPassword = "1234",
            name = "Person",
            department = "Platform Service Dev 7",
            role = DEVELOPER
        )

        val expectedUser = User(
            loginId = "OkestroUser",
            loginPassword = "encryptedPassword",
            name = "Person",
            department = "Platform Service Dev 7",
            role = DEVELOPER
        )

        every { registerUserPort.registerUser(userCommand) } returns expectedUser

        When("회원 가입에 필요한 정보를 전송하면") {
            val expectedResult = registerUserUseCase.registerUser(userCommand)

            Then("회원 가입이 성공해야 한다") {
                expectedUser shouldBe expectedResult
                verify(exactly = 1) { registerUserPort.registerUser(userCommand) }
            }
        }
    }
})