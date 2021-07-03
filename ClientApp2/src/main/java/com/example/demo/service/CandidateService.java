package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.ResponseDto;

@Service
public class CandidateService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<CandidateDto> findAllCandidates() {
		@SuppressWarnings("unchecked")
		List<CandidateDto> candidateDtos = (List<CandidateDto>) restTemplate.exchange("http://localhost:9000/resource/candidate/findAll", HttpMethod.GET, null, ResponseDto.class).getBody().getOutput();
    	
		return candidateDtos;
	}
	

}
