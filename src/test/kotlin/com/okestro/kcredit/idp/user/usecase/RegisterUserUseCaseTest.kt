package com.okestro.kcredit.idp.user.usecase

import com.okestro.kcredit.idp.common.utils.UserPasswordCrypto
import com.okestro.kcredit.idp.user.application.port.`in`.model.RegisterUserCommand
import com.okestro.kcredit.idp.user.application.port.out.RegisterUserPort
import com.okestro.kcredit.idp.user.application.service.RegisterUserService
import com.okestro.kcredit.idp.user.domain.Role
import com.okestro.kcredit.idp.user.domain.User
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class RegisterUserUseCaseTest {


    @MockK
    private lateinit var registerUserPort: RegisterUserPort

    @MockK
    private lateinit var passwordCrypto: UserPasswordCrypto

    @InjectMockKs
    private lateinit var registerUserUseCase: RegisterUserService

    @Test
    fun `관리자 회원 가입 테스트`() {
        // given
        val userCommand = RegisterUserCommand(
            loginId = "Josh",
            loginPassword = "1234",
            name = "person1",
            department = "People & Culture",
            role = Role.DEVELOPER
        )

        // PasswordEncoder Stubbing
        every { passwordCrypto.encryptPassword("1234")} returns "encryptedPassword"

        val expectedUser = User(
            loginId = "Josh",
            loginPassword = passwordCrypto.encryptPassword("1234"),
            name = "person1",
            department = "People & Culture",
            role = Role.DEVELOPER
        )

        // RegisterUserPort Stubbing
        every { registerUserPort.registerUser(userCommand) } returns expectedUser


        // when
        val expectedResult = registerUserUseCase.registerUser(userCommand)

        // then
        assertThat(expectedResult).isEqualTo(expectedUser)
        verify { passwordCrypto.encryptPassword("1234") }
    }
}