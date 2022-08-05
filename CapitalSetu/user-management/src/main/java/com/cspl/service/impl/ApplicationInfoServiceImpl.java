package com.cspl.service.impl;

import com.cspl.commons.exception.BaseException;
import com.cspl.constants.Constants;
import com.cspl.dao.ApplicationInfoDao;
import com.cspl.dao.impl.CommonDao;
import com.cspl.exception.InvalidAddressTypeException;
import com.cspl.models.Address;
import com.cspl.models.AddressInfo;
import com.cspl.models.ApplicationInfo;
import com.cspl.models.BusinessAddress;
import com.cspl.models.BusinessPersoanlInfo;
import com.cspl.models.ContactInfo;
import com.cspl.models.EmailInfo;
import com.cspl.models.GstInfo;
import com.cspl.models.MobileInfo;
import com.cspl.models.PersonInfo;
import com.cspl.models.proprietorshipInfo;
import com.cspl.request.AddressDetailsRequest;
import com.cspl.request.BusinessAddressDetailsRequest;
import com.cspl.request.BusinessPerRequest;
import com.cspl.request.ContactInfoRequest;
import com.cspl.request.EmailRequest;
import com.cspl.request.GstDetailsRequest;
import com.cspl.request.MobileInfoRequest;
import com.cspl.request.PersonDetailsRequest;
import com.cspl.request.ProprietorshipRequest;
import com.cspl.service.ApplicationInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

/**
 * OnBoarding related API's
 */
@Service
@Slf4j
public class ApplicationInfoServiceImpl implements ApplicationInfoService {

	@Autowired
	private ApplicationInfoDao applicationInfoDao;

	@Autowired
	private CommonDao commonDao;

	/**
	 * save email @throw Exception or else save details
	 *
	 * @param request Input request
	 * @throws BaseException
	 */
	@Override
	public void saveEmail(EmailRequest request) throws BaseException {
		log.info("Entering into [ApplicationInfoServiceImpl -> saveEmail] {}", request.getEmail());
		EmailInfo info=new EmailInfo(request); 
		commonDao.save(info);
	}

	/**
	 * save business personal info @throw Exception or else save details
	 *
	 * @param request Input request
	 * @throws BaseException
	 */
	@Override
	public void saveBusinessPerInfo(BusinessPerRequest request) throws BaseException {
		log.info("Entering into [ApplicationInfoServiceImpl -> saveBusinessPerInfo] {}", request.getApplicationId());

		BusinessPersoanlInfo info=new BusinessPersoanlInfo(request.getName(),request.getDob(),request.getBusinessType(),request.getPinCode());
		Map<String, Object> dataToUpdate = new HashMap<>();
		dataToUpdate.put("businessPersonalInfo", info);
		applicationInfoDao.updateApplicationInfo(request.getApplicationId(), dataToUpdate);
	}

	/**
	 * save proprietorship info @throw Exception or else save details
	 *
	 * @param request Input request
	 * @throws BaseException
	 */

	@Override
	public void saveProprietorshipInfo(ProprietorshipRequest request) throws BaseException {
		log.info("Entering into [ApplicationInfoServiceImpl -> saveProprietorshipInfo] {}", request.getApplicationId());

		proprietorshipInfo info=new proprietorshipInfo();
		info.setDob(request.getDob());
		info.setEmail(request.getEmail());

		Map<String, Object> dataToUpdate = new HashMap<>();
		dataToUpdate.put("ProprietorshipInfo", info);
		applicationInfoDao.updateApplicationInfo(request.getApplicationId(), dataToUpdate);

	}

	/**
	 * save Mobile Number @throw Exception or else save details
	 *
	 * @param request Input request
	 * @throws BaseException
	 */

	@Override
	public void saveMobile(MobileInfoRequest request) throws BaseException {
		log.info("Entering into [ApplicationInfoServiceImpl -> saveMobile] {}", request.getPhone());

		MobileInfo mobileInfo=new MobileInfo();
		mobileInfo.setInfoRequest(request);
		commonDao.save(mobileInfo);
	}

	/**
	 * save Contact info details @throw Exception or else save details
	 *
	 * @param request Input request
	 * @throws BaseException
	 */

	@Override
	public void saveContact(ContactInfoRequest request) throws BaseException {
		log.info("Entering into [ApplicationInfoServiceImpl -> saveContact] {}", request.getPhone());

		ContactInfo contactInfo=new ContactInfo();
		contactInfo.setContactInfoRequest(request);
		commonDao.save(contactInfo);
	}

	/**
	 * Check if user does not Exists @throw Exception or else save details
	 *
	 * @param request Input request
	 * @throws BaseException
	 */

	@Override
	public void saveGstInfo(@Valid GstDetailsRequest request) throws BaseException {

		log.info("Entering into [ApplicationInfoServiceImpl -> updateGstDetails] {}", request.getApplicationId());

		GstInfo gstinfo = new GstInfo();
		gstinfo.setEntityName(request.getName()).setBusinessType(request.getBussinessType())
		.setEmail(request.getEmail())
		.setIsVerified(false)
		.setGstRegDate(request.getDate());

		Map<String, Object> dataToUpdate = new HashMap<>();
		dataToUpdate.put("gstInfo", gstinfo);
		applicationInfoDao.updateApplicationInfo(request.getApplicationId(), dataToUpdate);

	}

	/**
	 * Check if address is invalid  @throw Exception
	 * or else save address
	 *
	 * @param request input type
	 * @param request input request
	 * @throws BaseException
	 */
	@Override
	public void saveAddressInfo(String type, AddressDetailsRequest request) throws BaseException {
		log.info("Entering into [ApplicationInfoServiceImpl -> updateAddressDetails] {}", request.getApplicationId());
		if (!Constants.AddressType.isValidAddressType(type)) {
			throw new InvalidAddressTypeException();
		}

		Address address = new Address();
		address.setLine1(request.getAddressLine1())
		.setLine2(request.getAddressLine2())
		.setLandmark(request.getLandmark())
		.setPincode(request.getPincode());


		ApplicationInfo applicationInfo = applicationInfoDao.getApplicationInfoById(request.getApplicationId());
		AddressInfo addressInfo = new AddressInfo();
		if (applicationInfo != null && applicationInfo.getAddressInfo() != null) {
			addressInfo = applicationInfo.getAddressInfo();
		}

		if (StringUtils.equalsIgnoreCase(type, Constants.AddressType.CURRENT.getAddressType())) {
			addressInfo.setCurrent(address);
			if ((request.getIsCurrentAddressSameAsPermanent() != null) && (request.getIsCurrentAddressSameAsPermanent())) {
				addressInfo.setPermanent(address);
				addressInfo.setIsCurrentAddressSameAsPermanent(request.getIsCurrentAddressSameAsPermanent());
			} else
				addressInfo.setIsCurrentAddressSameAsPermanent(request.getIsCurrentAddressSameAsPermanent());


		}

		if (StringUtils.equalsIgnoreCase(type, Constants.AddressType.BUSINESS.getAddressType())) {
			addressInfo.setBusiness(address);
		}
		if (StringUtils.equalsIgnoreCase(type, Constants.AddressType.PERMANENT.getAddressType())) {
			addressInfo.setPermanent(address);
			if ((request.getIsPermanentAddressSameAsBusiness() != null) && (request.getIsPermanentAddressSameAsBusiness())) {
				addressInfo.setBusiness(address);
				addressInfo.setIsPermanentAddressSameAsBusiness(request.getIsPermanentAddressSameAsBusiness());

			} else
				addressInfo.setIsPermanentAddressSameAsBusiness(request.getIsPermanentAddressSameAsBusiness());

		}
		Map<String, Object> dataToUpdate = new HashMap<>();
		dataToUpdate.put("addressInfo", addressInfo);
		applicationInfoDao.updateApplicationInfo(request.getApplicationId(), dataToUpdate);
	}
	/**
	 * Check if business address is invalid  @throw Exception
	 * or else save business address
	 *
	 * @param request input request
	 * @throws BaseException
	 */

	@Override
	public void saveBusinessAddressInfo(BusinessAddressDetailsRequest request) throws BaseException {
		log.info("Entering into [ApplicationInfoServiceImpl -> updateBusinessAddressDetails] {}", request.getApplicationId());

		BusinessAddress businessAddress = new BusinessAddress();		
		businessAddress.setBusinessName(request.getBusinessName())
		.setAddress(request.getAddress())
		.setLandmark(request.getLandmark())
		.setEmailId(request.getEmailId());


		ApplicationInfo applicationInfo = applicationInfoDao.getApplicationInfoById(request.getApplicationId());
		Map<String, Object> dataToUpdate = new HashMap<>();
		dataToUpdate.put("businessAddress", businessAddress);
		applicationInfoDao.updateApplicationInfo(request.getApplicationId(), dataToUpdate);

	}
	/**
	 * Check if person details is invalid  @throw Exception
	 * or else save person details
	 *
	 * @param request input request
	 * @throws BaseException
	 */

	@Override
	public void savePersonInfo(PersonDetailsRequest request) throws BaseException {
		log.info("Entering into [ApplicationInfoServiceImpl -> personDetails] {}", request.getApplicationId());
		PersonInfo personInfo = new PersonInfo();

		personInfo.setName(request.getName())
		.setPan(request.getPan())
		.setDob(request.getDob())
		.setEmailId(request.getEmailId());

		ApplicationInfo applicationInfo = applicationInfoDao.getApplicationInfoById(request.getApplicationId());
		Map<String, Object> dataToUpdate = new HashMap<>();
		dataToUpdate.put("personInfo", personInfo);
		applicationInfoDao.updateApplicationInfo(request.getApplicationId(), dataToUpdate);

	}


}
