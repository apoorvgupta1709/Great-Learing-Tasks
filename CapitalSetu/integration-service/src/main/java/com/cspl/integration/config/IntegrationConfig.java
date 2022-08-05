package com.cspl.integration.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

/**
 * Application level configs
 * @author Ashutosh
 */
@Component
@Getter
public class IntegrationConfig {

	@Value("${byPass.services}")
	private String byPassServices;

	private List<String> allowedServices;

	@PostConstruct
	private void setAllowedServices(){
		if(StringUtils.isNotEmpty(byPassServices)){
			allowedServices=new ArrayList<>();
			String[] split=byPassServices.split(",");
			allowedServices.addAll(Arrays.asList(split));
		}
	}


	@Value("${karza.base.URL}")
	private String karzaBaseURL;

	@Value("${karza.base.test.URL}")
	private String karzaTestBaseURL;

	@Value("${karza.header.value}")
	private String karzaHeaderValue;

	@Value("${karza.pan}")
	private String karzaPanURL;
	
	@Value("${karza.panAuthentication}")
	private String karzaPanAuthenticateURL;

	@Value("${karza.panBasedGst}")
	private String karzaPanBasedGstURL;

	@Value("${karza.pan.success.code}")
	private String panStatusCode;
	
	@Value("${karza.gstVerification}")
	private String karzaGstVerification;
	
	private int gststatusCode=101;
	
}
