package com.cspl.filters;

import static com.cspl.commons.util.Constants.OK;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.services.cognitoidp.model.UserNotFoundException;
import com.cspl.commons.response.GenericResponse;
import com.cspl.constants.UserConfig;
import com.cspl.service.helper.SignupServicehelper;
import com.cspl.utils.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import lombok.extern.slf4j.Slf4j;

@Component
@Order(1)
@Slf4j
public class AuthFilter extends OncePerRequestFilter {

	@Autowired
	private UserConfig userConfig;

	@Autowired
	private HttpRequestUtil httpRequestUtil;

	@Autowired
	private SignupServicehelper jwtTokenProvider;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {

		String header = request.getHeader("Authorization");
		log.info("Entering into [SignUpServiceImpl -> FILTERRRRRRRRR] {}", header);


		if (header == null || !header.startsWith("Bearer ")) {
			//ilterChain.doFilter(request, response);
			//request.getRequestDispatcher(request.getRequestURI());
			//throw new UserNotFoundException("No header");

			//out.print("user not found");

			//
			//	PrintWriter out=response.getWriter(); 
			//			response.setContentType("application/json");
			//			String json = "{\"data\": \"invalid\"}";
			//			out.write(json);
			//return;
			
			
			//new GenericResponse(OK, "Password saved successfully");


		}


		//Authentication auth =jwtTokenProvider.getAuthentication(jwtTokenProvider.resolveToken(request));
		//SecurityContextHolder.getContext().setAuthentication(auth);

		filterChain.doFilter(request, response);
	}
}
