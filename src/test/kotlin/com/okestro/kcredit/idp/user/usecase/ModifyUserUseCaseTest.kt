package com.okestro.kcredit.idp.user.usecase

import com.okestro.kcredit.idp.common.utils.UserPasswordCrypto
import com.okestro.kcredit.idp.user.application.port.`in`.model.ModifyUserCommand
import com.okestro.kcredit.idp.user.application.port.`in`.usecase.LoadUserUseCase
import com.okestro.kcredit.idp.user.application.port.out.ModifyUserPort
import com.okestro.kcredit.idp.user.application.service.ModifyUserService
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
class ModifyUserUseCaseTest {

    @MockK
    private lateinit var modifyUserPort: ModifyUserPort

    @MockK
    private lateinit var loadUserUseCase: LoadUserUseCase

    @MockK
    private lateinit var userPasswordCrypto: UserPasswordCrypto

    @InjectMockKs
    private lateinit var modifyUserUseCase: ModifyUserService

    @Test
    fun `회원 정보 수정 테스트`() {
        // given
        val expectedUser = User(
            loginId = "Josh",
            loginPassword = "wrongPassword",
            name = "person1",
            department = "People & Culture",
            role = Role.DEVELOPER
        )

        every { loadUserUseCase.loadUserById(1L) } returns expectedUser
        every { userPasswordCrypto.encryptPassword("1234") } returns "encryptedPassword"

        // 수정할 회원 정보 Command 객체 생성
        val userModifyCommand = ModifyUserCommand(
            loginPassword = "1234",
            name = "John",
            department = "GA"
        )

        // 변경될 회원 정보 객체 생성
        val modifiedUser = User(
            loginId = "Josh",
            loginPassword = "encryptedPassword",
            name = "John",
            department = "GA",
            role = Role.DEVELOPER
        )


        // 회원 정보 업데이트
        expectedUser.updateUser(
            loginPassword = userPasswordCrypto.encryptPassword(userModifyCommand.loginPassword),
            name = userModifyCommand.name,
            department = userModifyCommand.department
        )

        every { modifyUserPort.modifyUser(1L, expectedUser) } returns modifiedUser


        // when
        val expectedResult = modifyUserUseCase.modifyUser(1L, userModifyCommand)


        // then
        assertThat(expectedResult).isEqualTo(expectedResult)
        verify(exactly = 1) { loadUserUseCase.loadUserById(1L) }
        verify(exactly = 2) { userPasswordCrypto.encryptPassword("1234") }
    }
}