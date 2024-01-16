package com.okestro.kcredit.idp.common.utils

import com.okestro.kcredit.idp.common.annotation.Util
import com.okestro.kcredit.idp.user.domain.Role
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.Base64
import java.util.Date
import javax.crypto.spec.SecretKeySpec

@Util
class JwtUtil {

    private val secretKey = Base64.getEncoder()
        .encodeToString("secretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKeysecretKey".toByteArray())

    fun generateToken(loginId: String, role: Role) : String {
        // 토큰 유효기간을 1년으로 설정
        val tokenPeriod = 1000L * 60L * 60L * 24L * 30 * 12

        val claims = Jwts.claims().setSubject(loginId)
        claims["role"] = role.toString()

        val now = Date()

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + tokenPeriod))
            .signWith(SecretKeySpec(secretKey.toByteArray(), SignatureAlgorithm.HS256.jcaName))
            .compact()
    }

    fun verifyToken(token: String) =
        try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(secretKey.toByteArray())
                .build().parseClaimsJws(token)

            claims.body
                .expiration.after(Date())
        } catch (e: Exception) {
            false
        }

    fun extractLoginId(token: String) =
        Jwts.parserBuilder().setSigningKey(secretKey.toByteArray())
            .build().parseClaimsJws(token)
            .body.subject

    fun extractRole(token: String) =
        Jwts.parserBuilder().setSigningKey(secretKey.toByteArray())
            .build()
            .parseClaimsJws(token).body.get("role", String::class.java)
}