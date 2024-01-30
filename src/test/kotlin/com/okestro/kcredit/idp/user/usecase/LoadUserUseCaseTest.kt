package com.okestro.kcredit.idp.user.usecase

import com.appmattus.kotlinfixture.kotlinFixture
import com.okestro.kcredit.idp.common.exception.CustomException
import com.okestro.kcredit.idp.common.exception.ErrorCode.*
import com.okestro.kcredit.idp.user.application.port.out.LoadUserPort
import com.okestro.kcredit.idp.user.application.service.LoadUserService
import com.okestro.kcredit.idp.user.domain.User
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify


internal class LoadUserUseCaseTest : BehaviorSpec({

    isolationMode = IsolationMode.InstancePerLeaf

    val fixture = kotlinFixture()
    val loadUserPort = mockk<LoadUserPort>()
    val loadUserUseCase = LoadUserService(loadUserPort)

    Given("현재 가입되어 있는 사용자를 PK로 조회하려는 상황에서") {
        val userId = 1L
        val expectedUser = fixture<User>()

        every { loadUserPort.loadUserById(userId) } returns expectedUser

        When("존재하는 사용자의 PK로 조회를 시도하면") {
            val expectedResult = loadUserUseCase.loadUserById(userId)

            Then("해당 PK와 일치하는 사용자가 조회되어야 한다") {
                expectedUser shouldBe expectedResult
                verify(exactly = 1) { loadUserPort.loadUserById(userId) }
            }
        }
    }

    Given("사용자를 전체 조회하려는 상황에서") {
        val expectedUserList = listOf(
            fixture<User>(),
            fixture<User>(),
            fixture<User>()
        )

        every { loadUserUseCase.loadAllUsers() } returns expectedUserList

        When("조회를 시도하면") {
            val expectedResult = loadUserUseCase.loadAllUsers()

            Then("사용자의 전체 목록이 조회되어야 한다") {
                expectedUserList shouldBe expectedResult
                verify(exactly = 1) { loadUserPort.loadAllUsers() }
            }
        }
    }

    Given("현재 가입되어 있는 사용자를 아이디로 조회하려는 상황에서") {
        val expectedUser = fixture<User>()

        every { loadUserPort.loadUserByLoginId(expectedUser.loginId) } returns expectedUser

        When("사용자 아이디로 회원 조회를 시도하면") {
            val expectedResult = loadUserUseCase.loadUserByLoginId(expectedUser.loginId)

            Then("조회되는 사용자가 있어야 한다") {
                expectedUser shouldBe expectedResult
                verify(exactly = 1) { loadUserPort.loadUserByLoginId(expectedUser.loginId) }
            }
        }
    }

    Given("존재하지 않는 사용자의 PK로 사용자를 조회하려는 상황에서") {
        val userId = 1L
        every { loadUserPort.loadUserById(userId) } throws CustomException(USER_NOT_FOUND)

        When("존재하지 않는 사용자의 PK로 조회를 시도하면") {
            val exception = shouldThrow<CustomException> {
                loadUserUseCase.loadUserById(userId)
            }

            Then("예외가 발생해야 한다") {
                exception.errorCode shouldBe USER_NOT_FOUND
                verify(exactly = 1) { loadUserPort.loadUserById(userId) }
            }
        }
    }

    Given("존재하지 않는 사용자 아이디로 사용자를 조회하려는 상황에서") {
        val loginId = "OkestroUser"
        every { loadUserPort.loadUserByLoginId(loginId) } throws CustomException(USER_NOT_FOUND)

        When("존재하지 않는 사용자의 아이디로 조회를 시도하면") {
            val exception = shouldThrow<CustomException> {
                loadUserUseCase.loadUserByLoginId(loginId)
            }

            Then("예외가 발생해야 한다") {
                exception.errorCode shouldBe USER_NOT_FOUND
                verify(exactly = 1) { loadUserPort.loadUserByLoginId(loginId) }
            }
        }
    }
})