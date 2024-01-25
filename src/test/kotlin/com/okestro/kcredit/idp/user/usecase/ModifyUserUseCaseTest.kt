package com.okestro.kcredit.idp.user.usecase

import com.appmattus.kotlinfixture.kotlinFixture
import com.okestro.kcredit.idp.user.application.port.`in`.model.ModifyUserCommand
import com.okestro.kcredit.idp.user.application.port.`in`.usecase.LoadUserUseCase
import com.okestro.kcredit.idp.user.application.port.out.ModifyUserPort
import com.okestro.kcredit.idp.user.application.service.ModifyUserService
import com.okestro.kcredit.idp.user.domain.User
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.security.crypto.password.PasswordEncoder


class ModifyUserUseCaseTest : BehaviorSpec({

    val fixture = kotlinFixture()
    val loadUserUseCase = mockk<LoadUserUseCase>()
    val passwordCrypto = mockk<PasswordEncoder>()
    val modifyUserPort = mockk<ModifyUserPort>()
    val modifyUserUseCase = ModifyUserService(loadUserUseCase, modifyUserPort, passwordCrypto)

    Given("회원이 회원 정보를 변경하려는 상황에서") {
        val expectedUser = fixture<User>()

        every { loadUserUseCase.loadUserById(1L) } returns expectedUser
        every { passwordCrypto.encode("1234") } returns "encryptedPassword"

        val userModifyCommand = fixture<ModifyUserCommand> {
            property<ModifyUserCommand, String>("loginPassword") { "1234" }
        }

        val modifiedUser = fixture<User> {
            property<User, String>("loginPassword") { "encryptedPassword" }
            property<User, String>("name") { userModifyCommand.name }
            property<User, String>("department") { userModifyCommand.department }
        }

        expectedUser.updateUser(
            loginPassword = "encryptedPassword",
            name = userModifyCommand.name,
            department = userModifyCommand.department
        )

        every { modifyUserPort.modifyUser(1L, expectedUser) } returns modifiedUser

        When("수정할 새로운 정보를 입력해서 전송하면") {
            val expectedResult = modifyUserUseCase.modifyUser(1L, userModifyCommand)

            Then("회원 정보가 수정되어야 한다") {
                expectedResult.loginPassword shouldBe expectedUser.loginPassword
                expectedResult.name shouldBe expectedUser.name
                expectedResult.department shouldBe expectedUser.department
                verify { loadUserUseCase.loadUserById(1L) }
                verify { passwordCrypto.encode("1234") }
            }
        }
    }
})