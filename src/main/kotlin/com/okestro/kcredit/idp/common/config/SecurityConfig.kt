package com.okestro.kcredit.idp.common.config

import com.okestro.kcredit.idp.common.security.JwtAuthenticationFilter
import com.okestro.kcredit.idp.common.security.JwtExceptionFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val jwtExceptionFilter: JwtExceptionFilter
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity) : SecurityFilterChain {
        return http
            .httpBasic { httpBasic ->
                httpBasic.disable() }
            .csrf { csrf ->
                csrf.disable() }
            .formLogin { formLogin ->
                formLogin.disable() }
            .sessionManagement { sessionManage ->
                sessionManage.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests {
                request ->
                request.requestMatchers("/**").permitAll()
//                request.requestMatchers(HttpMethod.POST, "/api/login").permitAll()
//                request.requestMatchers("/api/users").hasRole("INTEGRATE_ADMIN")
//                request.requestMatchers("/api/jenkins/**").hasAnyRole("INTEGRATE_ADMIN", "DEVELOPER")
                request.anyRequest().authenticated() }
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun passwordEncoder() =
        PasswordEncoderFactories.createDelegatingPasswordEncoder()
}