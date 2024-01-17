package com.okestro.kcredit.idp

import com.okestro.kcredit.idp.user.application.port.`in`.model.RegisterUserCommand
import com.okestro.kcredit.idp.user.application.port.`in`.usecase.RegisterUserUseCase
import com.okestro.kcredit.idp.user.domain.Role
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
@ConfigurationPropertiesScan
class IdpApplication(
	private val registerUserUseCase: RegisterUserUseCase
) : CommandLineRunner {

	override fun run(vararg args: String?) {
		registerUserUseCase.registerUser(
			RegisterUserCommand(
				name = "Kyung Hoon, Cho",
				loginId = "Josh",
				loginPassword = "5555",
				department = "People & Culture",
				role = Role.INTEGRATE_ADMIN
			)
		)

		registerUserUseCase.registerUser(
			RegisterUserCommand(
				name = "Min Jae, Seo",
				loginId = "oke111",
				loginPassword = "1111",
				department = "Platform Service Dev 7",
				role = Role.DEVELOPER
			)
		)
	}

}

fun main(args: Array<String>) {
	runApplication<IdpApplication>(*args)
}
