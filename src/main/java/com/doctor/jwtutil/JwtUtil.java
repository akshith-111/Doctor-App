package com.doctor.jwtutil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;
	
//	private byte[] secretkey=Jwts.SIG.HS256.key().build().getEncoded();
//	String secret=Base64.getEncoder().encodeToString(secretkey);//store the secret key
	private SecretKey generateKey(){
		
		System.out.println(secret);
		SecretKey key=Keys.hmacShaKeyFor(secret.getBytes());
		return key;
		
	}
	
	
	public String generateToken(UserDetails details) {
		
		Map<String,Object> claims= new HashMap<>();
		claims.put("roles", details.getAuthorities());
		
		return Jwts.builder()
				.subject(details.getUsername())
				.claim("roles",claims)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+1000*60*60*2))
				.signWith(generateKey())
				.compact();
	}
	
	public boolean isValidToken(String token) {
		
		try {
			
			return Jwts.parser()
			.verifyWith(generateKey())
			.build()
			.parseSignedClaims(token)
			.getPayload()
			.getExpiration()
			.after(new Date());
			
			
		}catch (JwtException e) {
			return false;
		}
		
	}
	
	public String exractUsername(String token) {
		return Jwts.parser()
				.verifyWith(generateKey())
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
				
	}
	
//	public List<String> extractRoles(String token) {
//	
//		return Jwts.parser()
//				.verifyWith(generateKey())
//				.build()
//				.parseSignedClaims(token)
//				.getPayload()
//				.get("roles", List.class);
//	}
	
	
}
