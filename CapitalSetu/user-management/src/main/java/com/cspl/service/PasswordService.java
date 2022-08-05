package com.cspl.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import com.cspl.commons.exception.BaseException;
import com.cspl.dao.UserDao;
import com.cspl.request.SavePasswordRequest;

public interface PasswordService{
	
	/**
     * This method will trigger Password
     */
	void savePassword(SavePasswordRequest request)  throws BaseException;


}
