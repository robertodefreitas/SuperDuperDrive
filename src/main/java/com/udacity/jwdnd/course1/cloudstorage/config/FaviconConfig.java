package com.udacity.jwdnd.course1.cloudstorage.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

/**
 * SOURCES
 * https://www.baeldung.com/spring-boot-favicon
 * https://stackoverflow.com/questions/46302090/spring-boot-custom-favicon-ico-not-showing
 */
//@Configuration
public class FaviconConfig {
//	@Bean
//	public SimpleUrlHandlerMapping customFaviconHandlerMapping() {
//		SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
//		mapping.setOrder(Integer.MIN_VALUE);
//		mapping.setUrlMap(Collections.singletonMap(
//				"/favicon.ico", faviconRequestHandler()));
//		return mapping;
//	}

//	@Bean
//	protected ResourceHttpRequestHandler faviconRequestHandler() {
//		ResourceHttpRequestHandler requestHandler
//				= new ResourceHttpRequestHandler();
//		ClassPathResource classPathResource
//				= new ClassPathResource("/");
//		List<Resource> locations = Arrays.asList(classPathResource);
//		requestHandler.setLocations(locations);
//		return requestHandler;
//	}
}
