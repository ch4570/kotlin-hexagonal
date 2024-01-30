package com.okestro.kcredit.idp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class IdpApplication

fun main(args: Array<String>) {
	runApplication<IdpApplication>(*args)
}
