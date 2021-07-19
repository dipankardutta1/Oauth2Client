package com.example.demo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ClientApp2Application {

	public static void main(String[] args) {
		SpringApplication.run(ClientApp2Application.class, args);
	}
	
	
	
	@Bean
	RestTemplate restTemplate(OAuth2AuthorizedClientService clientService) {
		return new RestTemplateBuilder()
				.interceptors((ClientHttpRequestInterceptor) (httpRequest, bytes, execution) -> {

					OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(
							SecurityContextHolder.getContext().getAuthentication());

					OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
							token.getAuthorizedClientRegistrationId(),
							token.getName());

					httpRequest.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken().getTokenValue());

					return execution.execute(httpRequest, bytes);
				})
				.errorHandler(new RestTemplateResponseErrorHandler())
				.build();
	}
	
	
	
	
	class RestTemplateResponseErrorHandler  implements ResponseErrorHandler {
		
		
		@Override
	    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
			 return (
			          httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR 
			          || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
	    }

	    @Override
	    public void handleError(ClientHttpResponse httpResponse) throws IOException {
	    	
	    	
	    	if (httpResponse.getStatusCode() .series() == HttpStatus.Series.SERVER_ERROR) {
	    	            // handle SERVER_ERROR
	    	        } else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
	    	            // handle CLIENT_ERROR
						/*
						 * if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) { throw new
						 * NotFoundException(); }
						 */
	    	        }
	    	
	    	
	    }
	}

}
