package com.example.demo.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@EnableWebSecurity
public class UiSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
        .antMatchers("/login**","/","oauth2/authorization**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
				/*
				 * .sessionManagement() .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
				 * .and()
				 */
       // .oauth2ResourceServer()
        .oauth2Login();
    }

    /*@Bean
    WebClient webClient(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientRepository authorizedClientRepository) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 = new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository, authorizedClientRepository);
        oauth2.setDefaultOAuth2AuthorizedClient(true);
        return WebClient.builder().apply(oauth2.oauth2Configuration()).build();
    }*/
}
