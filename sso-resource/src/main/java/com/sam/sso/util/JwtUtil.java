package com.sam.sso.util;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author sumit
 *
 */
public class JwtUtil {

	public static String generateToken(String signingKey, String subject, Map<String, Object> usr_data) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		Claims claims = Jwts.claims().setSubject(subject);

		for (Map.Entry<String, Object> entry : usr_data.entrySet()) {
			claims.put(entry.getKey(), entry.getValue());
		}

		// .setSubject(subject)
		JwtBuilder builder = Jwts.builder().setClaims(claims).setIssuedAt(now).signWith(SignatureAlgorithm.HS256,
				signingKey);

		return builder.compact();
	}

	public static String getSubject(HttpServletRequest httpServletRequest, String jwtTokenCookieName,
			String signingKey) {
		String token = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
		if (token == null) {
			return null;
		}
		return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
	}
}