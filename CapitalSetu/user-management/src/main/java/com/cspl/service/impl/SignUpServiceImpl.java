package com.cspl.service.impl;

import static com.cspl.constants.Constants.USER_COLLECTION_NAME;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cspl.commons.exception.BaseException;
import com.cspl.constants.Constants;
import com.cspl.dao.UserDao;
import com.cspl.dao.impl.CommonDao;
import com.cspl.exception.UserNotFoundException;
import com.cspl.models.ApplicationInfo;
import com.cspl.models.SignUpInfo;
import com.cspl.models.User;
import com.cspl.request.LoginRequest;
import com.cspl.request.SignUpRequest;
import com.cspl.response.OtpResponse;
import com.cspl.service.SignUpService;
import com.cspl.service.helper.SignupServicehelper;
import com.cspl.service.helper.UserServiceHelper;
import org.springframework.security.core.Authentication;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SignUpServiceImpl implements SignUpService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private CommonDao commonDao;

	@Autowired
	private UserServiceHelper userServiceHelper;

	@Autowired
	private SignupServicehelper SignUpServiceHelper;

	/**
	 * This method is used to login
	 * 
	 * @param request input request
	 * @throws BaseException
	 */
	@Override
	public String login(LoginRequest request,HttpServletRequest servletRequest) throws BaseException {
		log.info("Entering into [SignUpServiceImpl -> login] {}", request.getMobileNumber());

		User user = userDao.findUserByMobileNumber(request.getMobileNumber());
		if(user==null) {
			throw new UserNotFoundException();
		}
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		if(encoder.matches(request.getPassword(), user.getPassword()))
		{
			//			String token =SignUpServiceHelper.createToken(request);
			//			log.info("Entering into [SignUpServiceImpl -> login] {}",token);
			//
			//			String resolveToken=SignUpServiceHelper.resolveToken(servletRequest);
			//			log.info("Entering into [SignUpServiceImpl -> resolve token] {}",resolveToken);
			//
			//			Authentication auth= SignUpServiceHelper.getAuthentication(resolveToken);
			//			String username=SignUpServiceHelper.getUsername(resolveToken);
			//			log.info("Entering into [SignUpServiceImpl -> username] {}",username);
			//
			//
			//			boolean b=false;
			//			try{
			//				b=SignUpServiceHelper.validateToken(resolveToken);
			//			}catch(Exception e)
			//			{
			//				log.info("Entering into [SignUpServiceImpl -> username] {}",e);
			//
			//			}
			//			log.info("Entering into [SignUpServiceImpl -> auth] {}", auth);
			//			log.info("Entering into [SignUpServiceImpl -> bool] {}",b);
			//			

			return new OtpResponse(getSignUpInfo(user.getUserId(), user.getMobileNumber()).getId()).getApplicationId();
			//return user.getId(); 
		}else
			return "unsuccessful";

	}
	/**
	 * This method is used to save sign up details
	 * 
	 * @param request input request
	 * @throws BaseException
	 */
	@Override
	public OtpResponse signUp(SignUpRequest data, HttpServletRequest servletRequest) throws BaseException {
		User user = userDao.findUserByMobileNumber(data.getMobileNumber());
		if (user != null) {
			return new OtpResponse(user.getId());
		}
		Long seqId = commonDao.getNextSequenceId(USER_COLLECTION_NAME, User.class);
		user = new User(userServiceHelper.generateCustomUserId(seqId), data.getMobileNumber()
				, Constants.UserStatus.ACTIVE.getStatus(), servletRequest.getRemoteAddr());

		user.setLastVisitedIp(servletRequest.getRemoteAddr())
		.setLastStatusUpdatedDate(LocalDateTime.now());
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encryptedPassword = encoder.encode(data.getPassword());
		user.setPassword(encryptedPassword);

		commonDao.save(user);
		SignUpInfo signUpInfo = new SignUpInfo();
		signUpInfo.setUserId(user.getUserId());
		signUpInfo.setMobileNumber(user.getMobileNumber());
		signUpInfo.setBusinessType(data.getBusinessType());

		ApplicationInfo applicationInfo=new ApplicationInfo();
		applicationInfo.setUserId(user.getUserId());
		applicationInfo.setMobileNumber(user.getMobileNumber());
		applicationInfo.setSignUpInfo(signUpInfo);

		try {
			log.info("Entering into [SignUpServiceImpl -> exceppppppppppp] {}", applicationInfo);

			commonDao.save(applicationInfo);

		}
		catch(Exception e)
		{
			log.info("Entering into [SignUpServiceImpl -> tionnnnnnnnnnnnn] {}", e);

		}
		return new OtpResponse(getSignUpInfo(user.getUserId(), user.getMobileNumber()).getId());
	}


	@SneakyThrows
	private ApplicationInfo getSignUpInfo(String userId, String mobileNumber){
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(userId));
		query.addCriteria(Criteria.where("mobileNumber").is(mobileNumber));
		return commonDao.findOne(query, ApplicationInfo.class);
	}

}
