package com.okestro.kcredit.idp.common.security

import com.okestro.kcredit.idp.common.annotation.Filter
import com.okestro.kcredit.idp.common.exception.ErrorCode.*
import com.okestro.kcredit.idp.common.exception.JwtException
import com.okestro.kcredit.idp.common.utils.JwtUtil
import com.okestro.kcredit.idp.user.application.service.LoadUserService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter

@Filter
class JwtAuthenticationFilter (
    private val jwtUtil: JwtUtil,
    private val loadUserService: LoadUserService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization")

        if (!StringUtils.hasText(token)) {
            doFilter(request, response, filterChain)
            return
        }

        val isAuthorized = jwtUtil.verifyToken(token)

        if (!isAuthorized) throw JwtException(INVALID_TOKEN)

        val user = loadUserService.loadUserByLoginId(jwtUtil.extractLoginId(token))

        val securityUser = UsernamePasswordAuthenticationToken(
            user, null, listOf(SimpleGrantedAuthority("ROLE_${user.role}"))
        )

        SecurityContextHolder.getContext().authentication = securityUser

        filterChain.doFilter(request, response)
    }

}