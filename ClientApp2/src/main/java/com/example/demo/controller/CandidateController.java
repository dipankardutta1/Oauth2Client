package com.example.demo.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.CandidateFormDto;
import com.example.demo.dto.PagableResponseDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.CandidateService;

@Controller
@RequestMapping("/candidateService")
public class CandidateController {


	@Autowired
	private CandidateService candidateService;

	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		//The date format to parse or output your dates
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		//Create a new CustomDateEditor
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		//Register it as custom editor for the Date type
		binder.registerCustomEditor(Date.class, editor);
	}

	
	
	
	@GetMapping("/search")
	public String openCandidateSearchPage(Model model,Principal principal) {

		
		
		
		return "candidateSearch";
	}
	
	
	
	
	@PostMapping("/search")
	public String candidateSearch(Model model,Principal principal,@RequestParam(required = false) String email,
			@RequestParam(required = false) String name,
			@RequestParam(required = false)String workexp,
			@RequestParam(required = false,defaultValue = "1")Integer page) {

		ResponseEntity<PagableResponseDto> responseDto=candidateService.searchCandidate(email,name,workexp,page);
		
		
		model.addAttribute("currentPage", page);
		model.addAttribute("candidateDtos",responseDto.getBody().getOutput());
		model.addAttribute("totalPages", responseDto.getBody().getTotalPages());
	    model.addAttribute("totalItems", responseDto.getBody().getTotalItems());
		
		return "candidateSearch";
	}
	
	
	@GetMapping("/search/paginate")
	public String candidateSearchPaginate(Model model,Principal principal,@RequestParam(required = false) String email,
			@RequestParam(required = false) String name,
			@RequestParam(required = false)String workexp,
			@RequestParam(required = true)Integer page) {

		ResponseEntity<PagableResponseDto> responseDto=candidateService.searchCandidate(email,name,workexp, page);
		
		
		model.addAttribute("currentPage", page);
		model.addAttribute("candidateDtos",responseDto.getBody().getOutput());
		model.addAttribute("totalPages", responseDto.getBody().getTotalPages());
	    model.addAttribute("totalItems", responseDto.getBody().getTotalItems());
		
		return "candidateSearch";
	}
	
	
	
	//this method 
	
	@RequestMapping("/profile")
	public String candidateProfile(Model model,Principal principal) {

		CandidateFormDto candidateDto=candidateService.findCandidateByEmail(principal.getName());
		System.out.print(candidateDto.getAliasName());
		System.out.print(candidateDto.getAddresses().get(3).getCountry());
		model.addAttribute("candidateDto",candidateDto);
		return "view";
	}
	
	@GetMapping("/profile/search")
	public String candidateProfileSearch(Model model,Principal principal,@RequestParam(required = true) String email) {

		CandidateFormDto candidateDto=candidateService.findCandidateByEmail(email);
		
		model.addAttribute("candidateDto",candidateDto);
		return "view";
	}



	@RequestMapping("/saveProfile")
	public String saveCandidateProfile(CandidateDto candidateDto) {

		ResponseEntity<ResponseDto> responseDto=candidateService.saveCandidate(candidateDto);

		return "candidate";
	}


}
