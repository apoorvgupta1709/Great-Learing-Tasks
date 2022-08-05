package com.cspl.service;

import com.cspl.commons.exception.BaseException;
import com.cspl.request.AddressDetailsRequest;
import com.cspl.request.BusinessAddressDetailsRequest;
import com.cspl.request.BusinessPerRequest;
import com.cspl.request.ContactInfoRequest;
import com.cspl.request.EmailRequest;
import com.cspl.request.GstDetailsRequest;
import com.cspl.request.MobileInfoRequest;
import com.cspl.request.PersonDetailsRequest;
import com.cspl.request.ProprietorshipRequest;

/**
 * This interface contains methods related to application details (save, Update)
 *
 */
public interface ApplicationInfoService {

	/**
	 * This method will save gst Details
	 */

	void saveGstInfo(GstDetailsRequest request)  throws BaseException;

	/**
	 * This method will save address details
	 */
	void saveAddressInfo(String type, AddressDetailsRequest request)  throws BaseException;

	/**
	 * This method will save business address details
	 */
	void saveBusinessAddressInfo(BusinessAddressDetailsRequest request)  throws BaseException;

	/**
	 * This method will save person details
	 */
	void savePersonInfo(PersonDetailsRequest request)  throws BaseException;

	/**
	 * This method will save contact info details
	 */
	void saveContact(ContactInfoRequest request)  throws BaseException;

	/**
	 * This method will save mobile Number 
	 */
	void saveMobile(MobileInfoRequest request)  throws BaseException;
	
	/**
	 * This method will save proprietorship Details
	 */
	void saveProprietorshipInfo(ProprietorshipRequest request)  throws BaseException;
	
	/**
	 * This method will save business personal info Details
	 */
	void saveBusinessPerInfo(BusinessPerRequest request)  throws BaseException;
	
	/**
	 * This method will save email 
	 */
	void saveEmail(EmailRequest request)  throws BaseException;

}
