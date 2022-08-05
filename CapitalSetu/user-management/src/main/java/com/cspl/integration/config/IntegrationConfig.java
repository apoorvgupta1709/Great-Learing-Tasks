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
 * @author Apoorv
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

	@Value("${Karza.PanVerification}")
	private String panVerif;
	
	@Value("${karza.gstList}")
	private String gstList;
	
	@Value("${karza.pan}")
	private String pan;
	
	@Value("${Karza.gstVerifaction}")
	private String gstVerification;

	
	
}
