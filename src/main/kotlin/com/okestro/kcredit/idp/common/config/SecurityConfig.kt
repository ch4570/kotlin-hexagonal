package com.okestro.kcredit.idp.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity) : SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .headers { headers -> headers.frameOptions {
                frameOptionsConfig -> frameOptionsConfig.disable() }
            }
            .authorizeHttpRequests { authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/**").permitAll()
            }.build()
    }

    @Bean
    fun passwordEncoder() =
        PasswordEncoderFactories.createDelegatingPasswordEncoder()
}