package com.cspl.service.helper;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cspl.dao.UserDao;
import com.cspl.dao.impl.CommonDao;
import com.cspl.models.User;
import com.cspl.request.LoginRequest;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


/**
 * This class contains Helper methods for signup service
 *
 * @author Apoorv
 */
@Service
@Slf4j
public class SignupServicehelper {

	String secretKey="secret-key";

	@Autowired
	private UserDao userDao;

	@Autowired
	private CommonDao commonDao;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String createToken(LoginRequest request) {


		Date now = new Date(0);
		Date validity = new Date(now.getTime() + 96000000);

		return Jwts.builder().setSubject(request.getMobileNumber())//
				.signWith(SignatureAlgorithm.HS256, secretKey)//
				.compact();
	}

	public Authentication getAuthentication(String token) {
		User user = userDao.findUserByMobileNumber(getUsername(token));

		return new UsernamePasswordAuthenticationToken(user, "");
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	public boolean validateToken(String token) throws Exception {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new Exception("Expired or invalid JWT token");
		}
	}

}
