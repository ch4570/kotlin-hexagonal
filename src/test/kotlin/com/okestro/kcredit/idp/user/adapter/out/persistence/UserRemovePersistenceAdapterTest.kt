package com.okestro.kcredit.idp.user.adapter.out.persistence

import com.appmattus.kotlinfixture.kotlinFixture
import com.okestro.kcredit.idp.user.adapter.out.persistence.adapter.command.UserRemovePersistenceAdapter
import com.okestro.kcredit.idp.user.adapter.out.persistence.entity.UserJpaEntity
import com.okestro.kcredit.idp.user.adapter.out.persistence.repository.UserRepository
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

@DataJpaTest
@Import(UserRemovePersistenceAdapter::class)
internal class UserRemovePersistenceAdapterTest(
    private val userRemovePersistenceAdapter: UserRemovePersistenceAdapter,
    private val userRepository: UserRepository
) : BehaviorSpec({

    val fixture = kotlinFixture()

    Given("회원을 삭제하려는 상황에서") {
        val savedUser = fixture<UserJpaEntity> {
            property<UserJpaEntity, Long?>("id") { null }
        }

        userRepository.save(savedUser)

        When("삭제하려는 회원의 PK를 전송하면") {
            val removeUserId = savedUser.id!!

            Then("회원이 삭제 되어야 한다") {
                userRemovePersistenceAdapter.removeUserById(removeUserId)
                val isUserPresent = userRepository.findById(removeUserId).isPresent

                isUserPresent shouldBe false
            }
        }
    }
})