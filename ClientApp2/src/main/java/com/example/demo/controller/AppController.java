package com.example.demo.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.FooModel;
import com.example.demo.dto.ResponseDto;
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
	
	@InitBinder
	private void dateBinder(WebDataBinder binder) {
	    //The date format to parse or output your dates
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    //Create a new CustomDateEditor
	    CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
	    //Register it as custom editor for the Date type
	    binder.registerCustomEditor(Date.class, editor);
	}

    @GetMapping("/foos")
    public String getFoos(Model model) {
        List<FooModel> foos = new ArrayList<>();
        
        foos.add(new FooModel(1l, "Dipanakr"));
        return "foos";
	
    }
    
    @RequestMapping("/securedPage")
    public String securedPage(Model model, Principal principal) {
    	
    	
    	
    	//List<CandidateDto> candidateDtos = candidateService.findAllCandidates();
    	
    	//model.addAttribute("candidateDtos", candidateDtos);
    	
    	model.addAttribute("user",principal.getName());
    	
        return "dashboard";
    	//return "securedPage";
    }
    @RequestMapping("/")
    public String index(Model model, Principal principal) {
    	
        return "index";
    }
    @RequestMapping("/candidateProfile/{user}")
    public String candidateProfile(Model model,@PathVariable("user")String user) {
    	
    	CandidateDto candidateDto=new CandidateDto();
    	/*CandidateDto candidateDto=new CandidateDto();
    	
    	ResponseEntity<ResponseDto> responseDto1=candidateService.getUserEmail(user);
    	String email=(String) responseDto1.getBody().getOutput();
    	candidateDto=candidateService.findCandidateByEmail(email);
    	
    	if(candidateDto==null) {
    		model.addAttribute("candidateDto",candidateDto);
    		return "candidate";
    	}else {
    		model.addAttribute("candidateDto",candidateDto);
    	}
    	*/
    	ResponseEntity<ResponseDto> responseDto1=candidateService.getUserEmail(user);
    	String email=(String) responseDto1.getBody().getOutput();
    	System.out.print(email);
    	model.addAttribute("candidateDto",candidateDto);
        return "candidate";
    }
   

    
    @RequestMapping("/saveCandidateProfile")
    public String saveCandidateProfile(CandidateDto candidateDto) {
    	
    	ResponseEntity<ResponseDto> responseDto=candidateService.saveCandidate(candidateDto);
    	
        return "candidate";
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
    
    
    
    @RequestMapping("/getAddress")
    public String getAddress() {
        return "address.html";
    }
    @RequestMapping("/getExperience")
    public String getExperience() {
        return "experience.html";
    }
    @RequestMapping("/getEducation")
    public String getEducation() {
        return "education.html";
    }
    
	/*
	 * @GetMapping("/login") public String getLoginPage(Model model) {
	 * 
	 * return "redirect:/oauth2/authorization/login-client"; }
	 */
}
