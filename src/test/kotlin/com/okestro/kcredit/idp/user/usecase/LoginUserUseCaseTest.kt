package com.okestro.kcredit.idp.user.usecase

import com.okestro.kcredit.idp.common.exception.CustomException
import com.okestro.kcredit.idp.common.utils.JwtUtil
import com.okestro.kcredit.idp.common.utils.UserPasswordCrypto
import com.okestro.kcredit.idp.user.application.port.`in`.model.LoginUserCommand
import com.okestro.kcredit.idp.user.application.port.`in`.usecase.LoadUserUseCase
import com.okestro.kcredit.idp.user.application.service.LoginUserService
import com.okestro.kcredit.idp.user.domain.Role
import com.okestro.kcredit.idp.user.domain.User
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class LoginUserUseCaseTest {

    @MockK
    private lateinit var loadUserUseCase: LoadUserUseCase

    @MockK
    private lateinit var passwordCrypto: UserPasswordCrypto

    @MockK
    private lateinit var jwtUtil : JwtUtil

    @InjectMockKs
    private lateinit var loginUserUseCase: LoginUserService

    @Test
    fun `사용자 로그인 테스트`() {
        // given
        val expectedUser = User(
            loginId = "Josh",
            loginPassword = "encryptedPassword",
            name = "person1",
            department = "People & Culture",
            role = Role.DEVELOPER
        )

        val loginUserCommand = LoginUserCommand(
            loginId = "Josh",
            loginPassword = "1234"
        )

        every { passwordCrypto.checkPassword(loginUserCommand.loginPassword, expectedUser.loginPassword) } returns true
        every { loadUserUseCase.loadUserByLoginId(loginUserCommand.loginId) } returns expectedUser
        every { jwtUtil.generateToken(expectedUser.loginId, Role.DEVELOPER) } returns "generatedToken"


        // when
        val expectedResult = loginUserUseCase.login(loginUserCommand)

        // then
        Assertions.assertThat(expectedResult).isEqualTo("generatedToken")
        verify(exactly = 1) { passwordCrypto.checkPassword("1234", "encryptedPassword") }
        verify(exactly = 1) { jwtUtil.generateToken("Josh", Role.DEVELOPER) }
        verify(exactly = 1) { loadUserUseCase.loadUserByLoginId("Josh") }
    }

    @Test
    fun `사용자 로그인 실패 테스트`() {
        // given
        val expectedUser = User(
            loginId = "Josh",
            loginPassword = "wrongPassword",
            name = "person1",
            department = "People & Culture",
            role = Role.DEVELOPER
        )

        val loginUserCommand = LoginUserCommand(
            loginId = "Josh",
            loginPassword = "1234"
        )

        every { passwordCrypto.checkPassword(loginUserCommand.loginPassword, "wrongPassword") } returns false
        every { loadUserUseCase.loadUserByLoginId(loginUserCommand.loginId) } returns expectedUser

        // when
        val exception = Assertions.assertThatThrownBy { loginUserUseCase.login(loginUserCommand) }

        // then
        exception.isInstanceOf(CustomException::class.java)
        verify(exactly = 1) { passwordCrypto.checkPassword(loginUserCommand.loginPassword, "wrongPassword") }
        verify(exactly = 1) { loadUserUseCase.loadUserByLoginId(loginUserCommand.loginId) }
    }
}