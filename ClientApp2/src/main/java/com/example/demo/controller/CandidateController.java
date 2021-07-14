package com.example.demo.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.CandidateFormDto;
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

	
	//this method 
	
	@RequestMapping("/profile")
	public String candidateProfile(Model model,Principal principal) {

		CandidateFormDto candidateDto=new CandidateFormDto();
			
		candidateDto=candidateService.findCandidateByEmail(principal.getName());
		System.out.print(candidateDto.getAliasName());
	
		//model.addAttribute("candidateDto",candidateDto);
		return "view";
	}



	@RequestMapping("/saveProfile")
	public String saveCandidateProfile(CandidateDto candidateDto) {

		ResponseEntity<ResponseDto> responseDto=candidateService.saveCandidate(candidateDto);

		return "candidate";
	}


}
