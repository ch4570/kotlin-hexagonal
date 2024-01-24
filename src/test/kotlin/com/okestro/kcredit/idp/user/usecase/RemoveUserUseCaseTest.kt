package com.okestro.kcredit.idp.user.usecase

import com.okestro.kcredit.idp.user.application.port.out.RemoveUserPort
import com.okestro.kcredit.idp.user.application.service.RemoveUserService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class RemoveUserUseCaseTest {

    @MockK
    private lateinit var removeUserPort: RemoveUserPort

    @InjectMockKs
    private lateinit var removeUserUseCase: RemoveUserService

    @Test
    fun `회원 삭제 테스트`() {
        // given
        every { removeUserPort.removeUserById(1L) } returns Unit

        // when
        val expected = removeUserUseCase.removeUserById(1L)

        // then
        assertThat(expected).isEqualTo(Unit)
        verify(exactly = 1) { removeUserPort.removeUserById(1L) }
    }
}