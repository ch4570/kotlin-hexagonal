package com.okestro.kcredit.idp.user.usecase

import com.okestro.kcredit.idp.common.exception.CustomException
import com.okestro.kcredit.idp.common.exception.ErrorCode
import com.okestro.kcredit.idp.user.application.port.out.LoadUserPort
import com.okestro.kcredit.idp.user.application.service.LoadUserService
import com.okestro.kcredit.idp.user.domain.Role
import com.okestro.kcredit.idp.user.domain.User
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class LoadUserUseCaseTest {

    @MockK
    private lateinit var loadUserPort: LoadUserPort

    @InjectMockKs
    private lateinit var loadUserUseCase: LoadUserService

    @Test
    fun `사용자 PK로 사용자 조회 테스트`() {
        // given
        val expectedUser = User(
            loginId = "Josh",
            loginPassword = "encryptedPassword",
            name = "person1",
            department = "People & Culture",
            role = Role.DEVELOPER
        )

        every { loadUserPort.loadUserById(1L) } returns expectedUser

        // when
        val expectedResult = loadUserUseCase.loadUserById(1L)

        // then
        Assertions.assertThat(expectedResult).isEqualTo(expectedUser)
        verify(exactly = 1) { loadUserPort.loadUserById(1L) }
    }

    @Test
    fun `사용자 전체 조회 테스트`() {
        // given
        val expectedUserList = listOf(
            User(loginId = "Josh",
                loginPassword = "encryptedPassword",
                name = "person1",
                department = "People & Culture",
                role = Role.DEVELOPER),
            User(loginId = "Rex",
                loginPassword = "encryptedPassword",
                name = "person2",
                department = "GA",
                role = Role.INTEGRATE_ADMIN),
            User(loginId = "Lauren",
                loginPassword = "encryptedPassword",
                name = "person3",
                department = "Clean Team",
                role = Role.DEVELOPER),
        )

        every { loadUserUseCase.loadAllUsers() } returns expectedUserList

        // when
        val expectedResult = loadUserUseCase.loadAllUsers()

        // then
        Assertions.assertThat(expectedUserList).isEqualTo(expectedResult)
        verify(exactly = 1) { loadUserPort.loadAllUsers() }
    }

    @Test
    fun `사용자 로그인 아이디로 사용자 조회 테스트`() {
        // given
        val expectedUser = User(
            loginId = "Josh",
            loginPassword = "encryptedPassword",
            name = "person1",
            department = "People & Culture",
            role = Role.DEVELOPER
        )

        every { loadUserPort.loadUserByLoginId(expectedUser.loginId) } returns expectedUser

        // when
        val expectedResult = loadUserUseCase.loadUserByLoginId(expectedUser.loginId)

        // then
        Assertions.assertThat(expectedUser).isEqualTo(expectedResult)
        verify(exactly = 1) { loadUserPort.loadUserByLoginId(expectedUser.loginId) }
    }

    @Test
    fun `사용자 PK로 사용자 조회 실패 테스트`() {
        // given
        val userId = 1L

        every { loadUserPort.loadUserById(userId) } throws CustomException(ErrorCode.USER_NOT_FOUND)

        // when
        val exception = Assertions.assertThatThrownBy { loadUserUseCase.loadUserById(userId) }

        // then
        exception.isInstanceOf(CustomException::class.java)
        verify(exactly = 1) { loadUserPort.loadUserById(userId) }
    }

    @Test
    fun `사용자 로그인 아이디로 사용자 조회 실패 테스트`() {
        // given
        val loginId = "Josh"

        every { loadUserPort.loadUserByLoginId(loginId) } throws CustomException(ErrorCode.USER_NOT_FOUND)

        // when
        val exception = Assertions.assertThatThrownBy { loadUserUseCase.loadUserByLoginId(loginId) }

        // then
        exception.isInstanceOf(CustomException::class.java)
        verify(exactly = 1) { loadUserPort.loadUserByLoginId(loginId) }
    }
}