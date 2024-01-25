package com.okestro.kcredit.idp.user.usecase

import com.okestro.kcredit.idp.user.application.port.out.RemoveUserPort
import com.okestro.kcredit.idp.user.application.service.RemoveUserService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify


class RemoveUserUseCaseTest : BehaviorSpec({

    val removeUserPort = mockk<RemoveUserPort>()
    val removeUserUseCase = RemoveUserService(removeUserPort)

    Given("관리자가 정상 가입된 회원을 삭제하려고 하는 상황에서") {
        val userId = 1L
        every { removeUserPort.removeUserById(userId) } returns Unit

        When("회원의 PK로 회원 삭제를 시도하면") {
            val expectedResult = removeUserUseCase.removeUserById(userId)

            Then("회원 삭제가 성공해야 한다") {
                expectedResult shouldBe Unit
                verify(exactly = 1) { removeUserPort.removeUserById(userId) }
            }
        }
    }
})