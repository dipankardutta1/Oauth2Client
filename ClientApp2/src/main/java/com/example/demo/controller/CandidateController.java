package com.example.demo.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.TokenExpireException;
import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.CandidateFormDto;
import com.example.demo.dto.PagableResponseDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.SearchDto;
import com.example.demo.service.CandidateService;

@Controller
@RequestMapping("/candidateService")
public class CandidateController {


	@Autowired
	private CandidateService candidateService;
	
	@Autowired
	private OAuth2AuthorizedClientService clientService;

	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		//The date format to parse or output your dates
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		//Create a new CustomDateEditor
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		//Register it as custom editor for the Date type
		binder.registerCustomEditor(Date.class, editor);
	}

	
	
	
	@GetMapping("/search")
	public String openCandidateSearchPage(Model model,Principal principal) {
		
		
		OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());
		
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());
        
        
        
        if(client.getAccessToken().getExpiresAt().compareTo(Instant.now()) > 0) {
        	SearchDto searchDto=new SearchDto();
    		model.addAttribute("searchDto",searchDto);
    		
    		return "candidateSearch";
        }else {
        	throw new TokenExpireException();
        }

		
	}
	
	
	
	
	@PostMapping("/search")
	public String candidateSearch(Model model,Principal principal,@RequestParam(required = false) String email,
			@RequestParam(required = false) String skil,
			@RequestParam(required = false)String workExp,
			@RequestParam(required = false,defaultValue = "1")Integer page) {

		
		OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());
		
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());
        
        
        
        if(client.getAccessToken().getExpiresAt().compareTo(Instant.now()) > 0) {
        	ResponseEntity<PagableResponseDto> responseDto=candidateService.searchCandidate(email,skil,workExp,page);
    		
        	
    		model.addAttribute("currentPage", page);
    		model.addAttribute("candidateDtos",responseDto.getBody().getOutput());
    		model.addAttribute("totalPages", responseDto.getBody().getTotalPages());
    	    model.addAttribute("totalItems", responseDto.getBody().getTotalItems());
    		
    		return "candidateSearch";
        }else {
        	throw new TokenExpireException();
        }

		
		
	}
	
	
	@GetMapping("/search/paginate")
	public String candidateSearchPaginate(Model model,Principal principal,@RequestParam(required = false) String email,
			@RequestParam(required = false) String name,
			@RequestParam(required = false)String workexp,
			@RequestParam(required = true)Integer page) {
		
		OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());
		
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());
        
        
        
        if(client.getAccessToken().getExpiresAt().compareTo(Instant.now()) > 0) {
        	ResponseEntity<PagableResponseDto> responseDto=candidateService.searchCandidate(email,name,workexp, page);
    		
    		
    		model.addAttribute("currentPage", page);
    		model.addAttribute("candidateDtos",responseDto.getBody().getOutput());
    		model.addAttribute("totalPages", responseDto.getBody().getTotalPages());
    	    model.addAttribute("totalItems", responseDto.getBody().getTotalItems());
    		
    		return "candidateSearch";
        }else {
        	throw new TokenExpireException();
        }

		
	}
	
	
	
	//this method 
	
	@RequestMapping("/profile")
	public String candidateProfile(Model model,Principal principal) {
		
		OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());
		
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());
        
        
        
        if(client.getAccessToken().getExpiresAt().compareTo(Instant.now()) > 0) {
        	CandidateFormDto candidateDto=candidateService.findCandidateByEmail(principal.getName());
    		
    		model.addAttribute("candidateDto",candidateDto);
    		
    		return "view";
        }else {
        	throw new TokenExpireException();
        }
		
		
		
		
		
	}
	
	@GetMapping("/profile/search")
	public String candidateProfileSearch(Model model,Principal principal,@RequestParam(required = true) String email) {
		
		
		OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());
		
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());
        
        
        
        if(client.getAccessToken().getExpiresAt().compareTo(Instant.now()) > 0) {
        	CandidateFormDto candidateDto=candidateService.findCandidateByEmail(email);
    		
    		model.addAttribute("candidateDto",candidateDto);
    		return "view";
        }else {
        	throw new TokenExpireException();
        }
		

		
	}



	@RequestMapping("/saveProfile")
	public String saveCandidateProfile(CandidateDto candidateDto) {
		
		OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());
		
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());
        
        
        
        if(client.getAccessToken().getExpiresAt().compareTo(Instant.now()) > 0) {
        	ResponseEntity<ResponseDto> responseDto=candidateService.saveCandidate(candidateDto);

    		return "candidate";
        }else {
        	throw new TokenExpireException();
        }
			
		
	}
	
	
	


}
