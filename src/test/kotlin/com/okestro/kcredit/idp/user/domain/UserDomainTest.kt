package com.okestro.kcredit.idp.user.domain

import com.appmattus.kotlinfixture.kotlinFixture
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class UserDomainTest : BehaviorSpec({

    val fixture = kotlinFixture()

    Given("회원 정보를 업데이트 하려는 상황에서") {
        val user = fixture<User>()

        When("수정할 회원 정보를 전송하면") {
            user.updateUser("1234", "Dev Seo Rex", "Platform Service Dev 7")

            Then("회원 정보가 수정되어야 한다") {
                user.loginPassword shouldBe "1234"
                user.name shouldBe "Dev Seo Rex"
                user.department shouldBe "Platform Service Dev 7"
            }
        }
    }

    Given("회원의 비밀번호를 초기화 하려는 상황에서") {
        val user = fixture<User>()

        When("회원 비밀번호 초기화를 시도하면") {
            user.revertPassword("1234")

            Then("비밀번호 초기화가 되어야 한다") {
                user.loginPassword shouldBe "1234"
            }
        }
    }

})