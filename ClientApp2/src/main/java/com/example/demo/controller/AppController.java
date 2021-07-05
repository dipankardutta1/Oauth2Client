package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.FooModel;
import com.example.demo.service.CandidateService;

@Controller
public class AppController {
	
	@Autowired
	private OAuth2AuthorizedClientService clientService;
	@Autowired
	private CandidateService candidateService;
	
	/*
	 * @Autowired private WebClient webClient;
	 */

    @GetMapping("/foos")
    public String getFoos(Model model) {
        List<FooModel> foos = new ArrayList<>();
        
        foos.add(new FooModel(1l, "Dipanakr"));
        return "foos";
	
    }
    
    @RequestMapping("/securedPage")
    public String securedPage(Model model, Principal principal) {
    	
    	
    	
    	List<CandidateDto> candidateDtos = candidateService.findAllCandidates();
    	
    	model.addAttribute("candidateDtos", candidateDtos);
    	
    	System.out.println(principal.getName() + candidateDtos);
    	
        return "securedPage";
    }
    @RequestMapping("/")
    public String index(Model model, Principal principal) {
        return "index";
    }
    
    @GetMapping("/revokeToken")
    public String logout(Model model,HttpRequest httpRequest) {
       
        //return "redirect:http://localhost:9999/auth/revoke-token";
    	
    	
    	OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());

		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());

		httpRequest.getHeaders().clear(); //(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken().getTokenValue());

		return "redirect:/";
    	
	
    }
    
	/*
	 * @GetMapping("/login") public String getLoginPage(Model model) {
	 * 
	 * return "redirect:/oauth2/authorization/login-client"; }
	 */
}
