package com.cspl.service.impl;

import static com.cspl.commons.util.Constants.CONTENT_TYPE_JSON;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspl.commons.exception.BaseException;
import com.cspl.commons.exception.RequestFailedException;
import com.cspl.commons.request.karza.GstVerificationRequest;
import com.cspl.commons.request.karza.PanAuthenticateRequest;
import com.cspl.commons.request.karza.PanRequest;
import com.cspl.commons.request.karza.PanResAuthenticate;
import com.cspl.commons.response.GstListWithGivenPan;
import com.cspl.commons.response.GstVeriResponse;
import com.cspl.commons.response.PanResponse;
import com.cspl.commons.service.HttpClientUtil;
import com.cspl.commons.util.ObjectMapperUtil;
import com.cspl.dao.ApplicationInfoDao;
import com.cspl.dao.impl.CommonDao;
import com.cspl.integration.config.IntegrationConfig;
import com.cspl.models.EntityDetails;
import com.cspl.models.GstListWithPan;
import com.cspl.models.PanBasedName;
import com.cspl.request.PanBasedNameRequest;
import com.cspl.request.ProprietorVerifyRequest;
import com.cspl.response.ActualEntityResponse;
import com.cspl.response.ActualPanVeriResponse;
import com.cspl.response.EntityDetailResponse;
import com.cspl.response.ProprietorEntityDetail;
import com.cspl.service.KarzaApiService;
import com.fasterxml.jackson.core.type.TypeReference;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KarzaApiServiceImpl implements KarzaApiService {

	@Autowired
	private CommonDao commonDao;
	@Autowired
	private ApplicationInfoDao applicationInfoDao;

	@Autowired
	private IntegrationConfig integrationConfig;


	/**
	 * call karza api to fetch pan based gst @throw Exception or else verify and save details
	 *
	 * @param request Input request
	 * @throws BaseException
	 */
	@Override
	public GstListWithGivenPan getGstList(PanRequest request) throws BaseException {
		log.info("Entering into [KarzaApiServiceImpl -> getGstList] for pan {} ", request.getUniqueId());

		String url = StringUtils.join(integrationConfig.getKarzaBaseURL(), integrationConfig.getGstList());
		GstListWithPan panResonse=null;

		try {
			String response = HttpClientUtil.post(url, request, null, CONTENT_TYPE_JSON);
			panResonse = ObjectMapperUtil.extractObject(response, new TypeReference<GstListWithPan>() {
			});
		}catch(Exception e)
		{
			throw new RequestFailedException();
		}
		Map<String, Object> dataToUpdate = new HashMap<>();
		dataToUpdate.put("GstList", panResonse);
		applicationInfoDao.updateApplicationInfo(request.getUniqueId(), dataToUpdate);

		return panResonse.getData();
	}

	/**
	 * call karza api to verify details @throw Exception or else verify and save details
	 *
	 * @param request Input request
	 * @throws BaseException
	 */
	@Override
	public PanResAuthenticate verifyPropDetails(ProprietorVerifyRequest request) throws BaseException {
		log.info("Entering into [KarzaApiServiceImpl -> verifyPropDetails] for pan {} ", request.getApplicationId());

		PanAuthenticateRequest panReq=new PanAuthenticateRequest();
		panReq.setUniqueId(request.getApplicationId());
		panReq.setConsent(request.getConsent());
		panReq.setDob(request.getDob());
		panReq.setName(request.getName());
		panReq.setPan(request.getPan());

		String url = StringUtils.join(integrationConfig.getKarzaBaseURL(), integrationConfig.getPanVerif());
		ActualPanVeriResponse panResonse=null;

		try {
			String response = HttpClientUtil.post(url, panReq, null, CONTENT_TYPE_JSON);

			panResonse = ObjectMapperUtil.extractObject(response, new TypeReference<ActualPanVeriResponse>() {
			});
		}catch(Exception e)
		{
			throw new RequestFailedException();
		}
		Map<String, Object> dataToUpdate = new HashMap<>();
		dataToUpdate.put("panAuthenticateReponse", panResonse.getData());
		applicationInfoDao.updateApplicationInfo(request.getApplicationId(), dataToUpdate);

		return panResonse.getData();
	}
	/**
	 * call karza api to get pan based name @throw Exception or else save details
	 *
	 * @param request Input request
	 * @throws BaseException
	 */

	@Override
	public PanResponse getPanBasedName(PanRequest request) throws BaseException {
		log.info("Entering into [KarzaApiServiceImpl -> panBasedName] for uniqueId {} ", request.getUniqueId());


		String url = StringUtils.join(integrationConfig.getKarzaBaseURL(), integrationConfig.getPan());
		PanBasedName panResonse=null;

		try {
			String response = HttpClientUtil.post(url, request, null, CONTENT_TYPE_JSON);
			panResonse = ObjectMapperUtil.extractObject(response, new TypeReference<PanBasedName>() {
			});
		}catch(Exception e)
		{
			throw new RequestFailedException();
		}
		Map<String, Object> dataToUpdate = new HashMap<>();
		dataToUpdate.put("panBasedNameResponse", panResonse.getData());
		applicationInfoDao.updateApplicationInfo(request.getUniqueId(), dataToUpdate);

		return panResonse.getData();

	}

	/**
	 * call karza api to get entity details @throw Exception or else save entity details
	 *
	 * @param request Input request
	 * @throws BaseException
	 */

	@Override
	public ActualEntityResponse getEntityDetails(GstVerificationRequest request) throws BaseException {
		log.info("Entering into [KarzaApiServiceImpl -> getEntityDetails] for uniqueId {} ", request.getUniqueId());
		String url = StringUtils.join(integrationConfig.getKarzaBaseURL(), integrationConfig.getGstVerification());
		
		//Actual response to be sent
		ActualEntityResponse actualEntityResponse=new ActualEntityResponse();
		
		//Response from karza
		EntityDetails entityDetails=new EntityDetails();
		//Data to be saved in database (partner ship, public)
		EntityDetailResponse entityDetailResponse=new EntityDetailResponse();
		
		try {
			String response = HttpClientUtil.post(url, request, null, CONTENT_TYPE_JSON);
			entityDetails = ObjectMapperUtil.extractObject(response, new TypeReference<EntityDetails>() {
			});

		}catch(Exception e)
		{
			throw new RequestFailedException();
		}

		actualEntityResponse.setBusinessName(entityDetails.getData().getTradeNam());
		actualEntityResponse.setBusinessType(entityDetails.getData().getCtb());
		actualEntityResponse.setUserName(entityDetails.getData().getLgnm());
		String pan=entityDetails.getData().getGstin();
		pan=pan.substring(2,pan.length()-3);
		actualEntityResponse.setPan(pan);
		
		entityDetailResponse.setBusinessName(entityDetails.getData().getTradeNam());
		entityDetailResponse.setBusinessType(entityDetails.getData().getCtb());
		entityDetailResponse.setGstin(entityDetails.getData().getGstin());
        
		// Propreitor data to be saved 
		EntityDetailResponse proprietorEntityDetail=new EntityDetailResponse();
		
		proprietorEntityDetail.setBusinessName(entityDetails.getData().getTradeNam());
		proprietorEntityDetail.setBusinessType(entityDetails.getData().getCtb());
		proprietorEntityDetail.setGstin(entityDetails.getData().getGstin());
		proprietorEntityDetail.setUserName(entityDetails.getData().getLgnm());
		proprietorEntityDetail.setPan(pan);
		
		Map<String, Object> dataToUpdate = new HashMap<>();
		
		if(pan.charAt(3)=='P') {		
		dataToUpdate.put("entityDetails", proprietorEntityDetail);
		applicationInfoDao.updateApplicationInfo(request.getUniqueId(), dataToUpdate);
        }
        else
        {
        	actualEntityResponse.setUserName("NA");
        	actualEntityResponse.setPan("NA");		
        	dataToUpdate.put("entityDetails", entityDetailResponse);
		applicationInfoDao.updateApplicationInfo(request.getUniqueId(), dataToUpdate);
        }	
		return actualEntityResponse;

	}

}
