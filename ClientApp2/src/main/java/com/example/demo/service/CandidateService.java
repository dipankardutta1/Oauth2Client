package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.CandidateFormDto;
import com.example.demo.dto.PagableResponseDto;
import com.example.demo.dto.ResponseDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CandidateService {

	@Autowired
	private RestTemplate restTemplate;

	public List<CandidateDto> findAllCandidates() {
		@SuppressWarnings("unchecked")
		List<CandidateDto> candidateDtos = (List<CandidateDto>) restTemplate.exchange("http://localhost:9000/resource/candidate/findAll", HttpMethod.GET, null, ResponseDto.class).getBody().getOutput();

		return candidateDtos;
	}
	
	
	public List<CandidateDto> findCandidateByQuery() {
		@SuppressWarnings("unchecked")
		List<CandidateDto> candidateDtos = (List<CandidateDto>) restTemplate.exchange("http://localhost:9000/resource/candidate/findAll", HttpMethod.GET, null, ResponseDto.class).getBody().getOutput();

		return candidateDtos;
	}
	
	
	
	

	public  CandidateFormDto findCandidateByEmail(String email) {

		String requestUri = "http://localhost:9000/resource/candidate/find/fullCandidate/byEmail?email="+email;
		/*Map<String, String> urlParameters = new HashMap<>();
		urlParameters.put("email",email);

		ResponseEntity<ResponseDto> res = restTemplate.getForEntity(requestUri,
				ResponseDto.class,
				urlParameters);
*/
		ResponseEntity<ResponseDto> responseDto = restTemplate.exchange(requestUri, HttpMethod.GET,null, ResponseDto.class);
		
		 ObjectMapper mapper = new ObjectMapper();
		 mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		 
		 CandidateFormDto pojo = mapper.convertValue(responseDto.getBody().getOutput(), CandidateFormDto.class);
		 
		 
		//ResponseDto responseDto=new ResponseDto();
		// responseDto = restTemplate.getForObject("http://localhost:9000/resource/candidate/find/fullCandidate/byEmail/"+email, ResponseDto.class);
		return pojo;

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


	public ResponseEntity<PagableResponseDto> searchCandidate(String email, String skil, String workExp,Integer page) {
		
		
		ResponseEntity<PagableResponseDto> responseDto =  restTemplate.exchange("http://localhost:9000/resource/candidate/search/candidate?email="+email+"&skil="+skil+"&workExp="+workExp+"&page="+page, HttpMethod.GET, null, PagableResponseDto.class);

		return responseDto;
	}


}
