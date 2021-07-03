package com.example.demo.feign;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

import feign.auth.BasicAuthRequestInterceptor;

public class FeignClientConfig {
	
	/*
	  @Bean 
	  RequestInterceptor oauth2FeignRequestInterceptor() { 
		  return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resource());
	  }
	 */
	/*
	 @Bean
	    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
	        return new BasicAuthRequestInterceptor("clientapp", "password123");
	    }
	
	
	

	private OAuth2ProtectedResourceDetails resource() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
	      ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
	      resourceDetails.setUsername(username);
	      resourceDetails.setPassword(password);
	      resourceDetails.setAccessTokenUri("http://localhost:9999/auth/oauth/token");
	      resourceDetails.setClientId("clientapp");
	      resourceDetails.setClientSecret("password123");
	      resourceDetails.setGrantType("password");
	      resourceDetails.setScope(Arrays.asList("role_user"));
	      return resourceDetails;
	   }
	   
	   */
}
