package com.cspl.service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.cspl.commons.exception.BaseException;
import com.cspl.request.LoginRequest;
import com.cspl.request.SignUpRequest;
import com.cspl.request.TriggeredOtpRequest;
import com.cspl.response.OtpResponse;

public interface SignUpService {
	OtpResponse signUp(SignUpRequest  data, HttpServletRequest servletRequest) throws BaseException;

	String login(LoginRequest request,HttpServletRequest servletRequest) throws BaseException;


}
