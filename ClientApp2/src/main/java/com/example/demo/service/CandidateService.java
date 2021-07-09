package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

	public CandidateDto findCandidateByEmail(String email) {

		CandidateDto candidateDto = (CandidateDto) restTemplate.exchange("http://localhost:9000/resource/candidate/find/byEmail/"+email, HttpMethod.GET,null, ResponseDto.class).getBody().getOutput();

		return candidateDto;
	}
	public ResponseEntity<ResponseDto> saveCandidate(CandidateDto candidateDto) {
		HttpEntity<CandidateDto> httpEntity = new HttpEntity<CandidateDto>(candidateDto);

		ResponseEntity<ResponseDto> responseDto =  restTemplate.exchange("http://localhost:9000/resource/candidate/save", HttpMethod.POST,httpEntity, ResponseDto.class);

		return responseDto;
	}

	public ResponseEntity<ResponseDto> getUserEmail(String userName) {
		//HttpEntity<String> httpEntity = new HttpEntity<String>();

		ResponseEntity<ResponseDto> responseDto =  restTemplate.exchange("http://localhost:9000/resource/candidate/getEmailByUserName?username="+userName, HttpMethod.GET,null, ResponseDto.class);

		return responseDto;
	}


}
