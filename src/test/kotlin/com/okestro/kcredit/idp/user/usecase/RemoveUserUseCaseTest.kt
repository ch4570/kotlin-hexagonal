package com.okestro.kcredit.idp.user.usecase

import com.okestro.kcredit.idp.common.exception.CustomException
import com.okestro.kcredit.idp.common.exception.ErrorCode.*
import com.okestro.kcredit.idp.user.application.port.out.RemoveUserPort
import com.okestro.kcredit.idp.user.application.service.RemoveUserService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify


class RemoveUserUseCaseTest : BehaviorSpec({

    isolationMode = IsolationMode.InstancePerLeaf

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

    Given("관리자가 존재하지 않는 회원을 삭제하려고 하는 상황에서") {
        val userId = 92L
        every { removeUserPort.removeUserById(userId) } throws CustomException(USER_NOT_FOUND)

        When("회원의 PK로 회원 삭제를 시도하면") {
            val exception = shouldThrow<CustomException> {
                removeUserUseCase.removeUserById(userId)
            }

            Then("예외가 발생해야 한다") {
                exception.errorCode shouldBe USER_NOT_FOUND
                verify(exactly = 1) { removeUserPort.removeUserById(userId) }
            }
        }
    }
})