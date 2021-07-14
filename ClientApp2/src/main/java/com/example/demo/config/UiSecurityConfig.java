package com.example.demo.config;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;

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
        //.antMatchers("/candidate/**").access("hasAnyAuthority('role_user')")
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
        .deleteCookies("JSESSIONID")
        .and()
        .exceptionHandling().accessDeniedPage("/access-denied");
    }
    
    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
		return (authorities) -> {
			Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

			authorities.forEach(authority -> {
				if (OidcUserAuthority.class.isInstance(authority)) {
					OidcUserAuthority oidcUserAuthority = (OidcUserAuthority)authority;

					OidcIdToken idToken = oidcUserAuthority.getIdToken();
					OidcUserInfo userInfo = oidcUserAuthority.getUserInfo();

					// Map the claims found in idToken and/or userInfo
					// to one or more GrantedAuthority's and add it to mappedAuthorities

				} else if (OAuth2UserAuthority.class.isInstance(authority)) {
					OAuth2UserAuthority oauth2UserAuthority = (OAuth2UserAuthority)authority;

					Map<String, Object> userAttributes = oauth2UserAuthority.getAttributes();
					
					if (userAttributes.containsKey("authorities")){
                        List<Map<String,String>> authorityList = (List<Map<String,String>>) userAttributes.get("authorities");
                        for(Map<String,String> map :  authorityList) {
                        	mappedAuthorities.add(new SimpleGrantedAuthority(map.get("authority")));
                        }
                       
                    }

					// Map the attributes found in userAttributes
					// to one or more GrantedAuthority's and add it to mappedAuthorities

				}
			});

			return mappedAuthorities;
		};
    }
}
