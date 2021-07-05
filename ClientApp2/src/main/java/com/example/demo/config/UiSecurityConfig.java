package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

@EnableWebSecurity
public class UiSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${auth.server.domain}")
	private String authServerDomain;
	
	
	@Autowired
    ClientRegistrationRepository clientRegistrationRepository;
	
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	
    	System.out.println("authServerDomain " + authServerDomain);
    	
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
        .oauth2Login().and().logout()
        //.logoutSuccessHandler(oidcLogoutSuccessHandler())
        .logoutSuccessUrl(authServerDomain+"/revoke-token")
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .deleteCookies("JSESSIONID");
    }
    
    private OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() { 
        OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        successHandler.setPostLogoutRedirectUri("http://localhost:8000/");
        return successHandler;
    }

    /*@Bean
    WebClient webClient(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientRepository authorizedClientRepository) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 = new ServletOAuth2AuthorizedClientExchangeFilterFunction(clientRegistrationRepository, authorizedClientRepository);
        oauth2.setDefaultOAuth2AuthorizedClient(true);
        return WebClient.builder().apply(oauth2.oauth2Configuration()).build();
    }*/
}
