package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.FooModel;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.CandidateService;

@Controller
public class AppController {
	
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
    
    
    
	/*
	 * @GetMapping("/login") public String getLoginPage(Model model) {
	 * 
	 * return "redirect:/oauth2/authorization/login-client"; }
	 */
}
