package com.cspl.integration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cspl.integration.filter.AuthInterceptor;

@Configuration
@SuppressWarnings("deprecation")
public class WebAppConfig extends WebMvcConfigurerAdapter{
	@Autowired private AuthInterceptor authInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor);
	}

}