package com.example.demo.controller;

import java.security.Principal;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.service.CandidateService;

@RestController
public class CandidateRestController {
	
	@Autowired
	private CandidateService candidateService;
	
	

	@PostMapping("/api/candidate/summary/update")
	public ResponseEntity<?> saveCandidateSummary(Principal principal, @Valid @RequestBody CandidateDto candidateDto, Errors errors) {
		
		
		
		
		
		if (errors.hasErrors()) {
			ResponseDto responseDto = new ResponseDto();

			responseDto.setMsg(errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(responseDto);

        }
		candidateDto.setEmail(principal.getName());
		
		ResponseEntity<ResponseDto> responseDto=candidateService.updateSummary(candidateDto);
		responseDto.getBody().setOutput(candidateDto);

		return responseDto;
	}
	
	
	
	@PostMapping("/api/candidate/profile/update")
	public ResponseEntity<?> saveCandidateProfile(Principal principal, @Valid @RequestBody CandidateDto candidateDto, Errors errors) {
		
		
		
		
		
		if (errors.hasErrors()) {
			ResponseDto responseDto = new ResponseDto();

			responseDto.setMsg(errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(responseDto);

        }
		candidateDto.setEmail(principal.getName());
		
		ResponseEntity<ResponseDto> responseDto=candidateService.updateProfile(candidateDto);
		responseDto.getBody().setOutput(candidateDto);

		return responseDto;
	}
	
	
	
}
