package com.okestro.kcredit.idp.user.usecase

import com.appmattus.kotlinfixture.kotlinFixture
import com.okestro.kcredit.idp.user.application.port.`in`.usecase.LoadUserUseCase
import com.okestro.kcredit.idp.user.application.port.out.RevertUserPasswordPort
import com.okestro.kcredit.idp.user.application.service.RevertUserPasswordService
import com.okestro.kcredit.idp.user.domain.User
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import org.springframework.security.crypto.password.PasswordEncoder

class RevertUserPasswordUseCaseTest : BehaviorSpec({

    val fixture = kotlinFixture()
    val revertUserPasswordPort = mockk<RevertUserPasswordPort>()
    val loadUserUseCase = mockk<LoadUserUseCase>()
    val passwordCrypto = mockk<PasswordEncoder>()
    val revertUserPasswordUseCase = RevertUserPasswordService(loadUserUseCase, revertUserPasswordPort, passwordCrypto)


    Given("회원이 비밀번호를 초기화하려고 하는 상황에서") {
        val userId = 1L
        val loadUser = fixture<User>()
        every { loadUserUseCase.loadUserById(1L) } returns loadUser
        every { revertUserPasswordPort.revertUserPassword(1L, loadUser) } just Runs
        every { passwordCrypto.encode("1234") } returns "encryptedPassword"

        When("비밀번호 초기화를 시도하면") {
            revertUserPasswordUseCase.revertUserPassword(userId)

            Then("비밀번호 초기화가 되어야 한다") {
                loadUser.loginPassword shouldBe "encryptedPassword"
                verify(exactly = 1) { loadUserUseCase.loadUserById(1L) }
                verify(exactly = 1) { revertUserPasswordPort.revertUserPassword(1L, loadUser) }
                verify(exactly = 1) { passwordCrypto.encode("1234") }
            }
        }
    }
})