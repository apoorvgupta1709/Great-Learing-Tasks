package com.cspl.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.cspl.models.User;

import com.cspl.commons.exception.BaseException;
import com.cspl.dao.UserDao;
import com.cspl.exception.PasswordMismatchException;
import com.cspl.exception.UserNotFoundException;
import com.cspl.request.SavePasswordRequest;
import com.cspl.service.PasswordService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PasswordServiceImpl implements PasswordService {

	@Autowired
	private UserDao userDao;
	
	/**
	 * Check if user not found @throw Exception
	 * or else create user and save password
	 *
	 * @param request input request
	 * @throws BaseException
	 */
    
	@Override
	public void savePassword(SavePasswordRequest request) throws BaseException{
		log.info("Entering into [OtpServiceImpl -> triggerOtp] {}", request.getMobileNumber());
        User user=userDao.findUserByMobileNumber(request.getMobileNumber());
		if(user==null) {
			throw new UserNotFoundException();
		}
        if (!StringUtils.equals(request.getPassword(), request.getReEnteredPassword())) {
        throw new PasswordMismatchException();
        }
        
      	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encyptedPassword=encoder.encode(request.getPassword());
		Map<String,Object> dataToUpdate=new HashMap<>();
		dataToUpdate.put("password",encyptedPassword);
		userDao.updateUser(user.getUserId(), dataToUpdate);	
		
	}

}
