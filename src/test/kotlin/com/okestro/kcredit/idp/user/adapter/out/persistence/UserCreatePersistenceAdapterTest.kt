package com.okestro.kcredit.idp.user.adapter.out.persistence

import com.appmattus.kotlinfixture.kotlinFixture
import com.okestro.kcredit.idp.ci.common.util.PasswordCrypto
import com.okestro.kcredit.idp.user.adapter.out.persistence.adapter.command.UserCreatePersistenceAdapter
import com.okestro.kcredit.idp.user.adapter.out.persistence.mapper.UserMapper
import com.okestro.kcredit.idp.user.adapter.out.persistence.repository.UserRepository
import com.okestro.kcredit.idp.user.application.port.`in`.model.RegisterUserCommand
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

@DataJpaTest
@Import(UserCreatePersistenceAdapter::class, UserMapper::class)
internal class UserCreatePersistenceAdapterTest(
    private val userRepository: UserRepository,
    private val userCreatePersistenceAdapter: UserCreatePersistenceAdapter
) : BehaviorSpec({

    val fixture = kotlinFixture()

    Given("회원을 저장하려는 상황에서") {
        val registerUserCommand = fixture<RegisterUserCommand>()

        When("회원 정보를 전송하면") {
            val savedUser = userCreatePersistenceAdapter.registerUser(registerUserCommand)

            Then("회원이 저장 되어야 한다") {
                val findUser = userRepository.findByLoginId(savedUser.loginId).get()

                findUser.loginId shouldBe savedUser.loginId
                findUser.loginPassword shouldBe savedUser.loginPassword
                findUser.userName shouldBe savedUser.name
                findUser.department shouldBe savedUser.department
                findUser.role shouldBe savedUser.role
            }
        }
    }
})