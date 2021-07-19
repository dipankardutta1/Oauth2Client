package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.CandidateFormDto;
import com.example.demo.dto.ExperienceEntryDto;
import com.example.demo.dto.PagableResponseDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.SummaryDto;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CandidateService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${resouce.server.domain}")
	private String resouceServerDomain;
	
	
	@Value("${auth.server.domain}")
	private String authServerDomain;
	

	public List<CandidateDto> findAllCandidates() {
		
		String url = resouceServerDomain+"/candidate/findAll";
		
		@SuppressWarnings("unchecked")
		List<CandidateDto> candidateDtos = (List<CandidateDto>) restTemplate.exchange(url, HttpMethod.GET, null, ResponseDto.class).getBody().getOutput();

		return candidateDtos;
	}
	
	
	public List<CandidateDto> findCandidateByQuery() {
		
		String url = resouceServerDomain+"/candidate/findAll";
		
		@SuppressWarnings("unchecked")
		List<CandidateDto> candidateDtos = (List<CandidateDto>) restTemplate.exchange(url, HttpMethod.GET, null, ResponseDto.class).getBody().getOutput();

		return candidateDtos;
	}
	
	
	
	

	public  CandidateFormDto findCandidateByEmail(String email) {

		
		String requestUri = resouceServerDomain+"/candidate/find/fullCandidate/byEmail?email="+email;
		
		ResponseEntity<ResponseDto> responseDto = restTemplate.exchange(requestUri, HttpMethod.GET,null, ResponseDto.class);
		
		 CandidateFormDto pojo = null;
		
		if(responseDto.getStatusCode().compareTo(HttpStatus.OK) == 0) {
			
			
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			 
			pojo = mapper.convertValue(responseDto.getBody().getOutput(), CandidateFormDto.class);
		}else {
			
			
			
			
			requestUri = authServerDomain+"/user/getDetails?username="+email;
			
			responseDto = restTemplate.exchange(requestUri, HttpMethod.GET,null, ResponseDto.class);
			
			ObjectMapper mapper = new ObjectMapper();
			
			Map<String, String> map = mapper.convertValue(responseDto.getBody().getOutput(), Map.class);
			
			pojo = new CandidateFormDto();
			pojo.setFirstName(map.get("firstName"));
			pojo.setLastName(map.get("lastName"));
			pojo.setEmail(map.get("email"));
			pojo.setProfileTitle("NA");
			pojo.setSummary("NA");
			pojo.setWorkExperience("Fresher");
			pojo.setReleventExperience("Fresher");
			
			CandidateDto candidateDto = new CandidateDto();
			candidateDto.setFirstName(map.get("firstName"));
			candidateDto.setLastName(map.get("lastName"));
			candidateDto.setEmail(map.get("email"));
			candidateDto.setProfileTitle("NA");
			candidateDto.setSummary("NA");
			candidateDto.setWorkExperience("Fresher");
			candidateDto.setReleventExperience("Fresher");
			
			
			saveCandidate(candidateDto);
			
		}
		
		 
		 
		 
		
		 
		 
		//ResponseDto responseDto=new ResponseDto();
		// responseDto = restTemplate.getForObject("http://localhost:9000/resource/candidate/find/fullCandidate/byEmail/"+email, ResponseDto.class);
		return pojo;

	}
	
	
	
	
	public ResponseEntity<ResponseDto> saveCandidate(CandidateDto candidateDto) {
		
		String url = resouceServerDomain+"/candidate/save";
		
		HttpEntity<CandidateDto> httpEntity = new HttpEntity<CandidateDto>(candidateDto);

		ResponseEntity<ResponseDto> responseDto =  restTemplate.exchange(url, HttpMethod.POST,httpEntity, ResponseDto.class);

		return responseDto;
	}
	
	
	public ResponseEntity<ResponseDto> updateSummary(CandidateDto candidateDto) {
		String url = resouceServerDomain+"/candidate/find/byEmail/"+candidateDto.getEmail();
		
		ResponseEntity<ResponseDto> responseDto =  restTemplate.exchange(url, HttpMethod.GET,null, ResponseDto.class);

		if(responseDto.getStatusCode().compareTo(HttpStatus.OK) == 0) {
			 ObjectMapper mapper = new ObjectMapper();
			 mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			 
			 CandidateDto pojo = mapper.convertValue(responseDto.getBody().getOutput(), CandidateDto.class);
			 
			 pojo.setFirstName(candidateDto.getFirstName());
			 pojo.setLastName(candidateDto.getLastName());
			 pojo.setProfileTitle(candidateDto.getProfileTitle());
			 pojo.setSummary(candidateDto.getSummary());
			 
			 
				 
			 url = resouceServerDomain+"/candidate/update";
			 
			 HttpEntity<CandidateDto> httpEntity = new HttpEntity<CandidateDto>(pojo);
			 
			 responseDto =  restTemplate.exchange(url, HttpMethod.PUT,httpEntity, ResponseDto.class);
			
			 
			
			 
			 
			 return responseDto;
		}
		
		

		return responseDto;
	}

	public ResponseEntity<ResponseDto> getUserEmail(String userName) {
		String url = resouceServerDomain+"/candidate/getEmailByUserName?username="+userName;

		ResponseEntity<ResponseDto> responseDto =  restTemplate.exchange(url, HttpMethod.GET,null, ResponseDto.class);

		return responseDto;
	}


	public ResponseEntity<PagableResponseDto> searchCandidate(String email, String skil, String workExp,Integer page) {
		String url = resouceServerDomain+"/candidate/search/candidate?email="+email+"&skil="+skil+"&workExp="+workExp+"&page="+page;
		
		ResponseEntity<PagableResponseDto> responseDto =  restTemplate.exchange(url, HttpMethod.GET, null, PagableResponseDto.class);

		return responseDto;
	}


	public ResponseEntity<ResponseDto> updateProfile(CandidateDto candidateDto) {
		
		String url = resouceServerDomain+"/candidate/find/byEmail/"+candidateDto.getEmail();
		
		ResponseEntity<ResponseDto> responseDto =  restTemplate.exchange(url, HttpMethod.GET,null, ResponseDto.class);

		if(responseDto.getStatusCode().compareTo(HttpStatus.OK) == 0) {
			 ObjectMapper mapper = new ObjectMapper();
			 mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			 
			 CandidateDto pojo = mapper.convertValue(responseDto.getBody().getOutput(), CandidateDto.class);
			 
			 pojo.setAliasName(candidateDto.getAliasName());
			 pojo.setInterViewMode(candidateDto.getInterViewMode());
			 pojo.setInterviewStatuses(candidateDto.getInterviewStatuses());
			 pojo.setBirthDate(candidateDto.getBirthDate());
			 pojo.setWorkExperience(candidateDto.getWorkExperience());
			 pojo.setReleventExperience(candidateDto.getReleventExperience());
			 pojo.setHiringType(candidateDto.getHiringType());
			 
			 if(pojo.getCandidateId() == null || pojo.getCandidateId().isEmpty()) {
				 
				 url = resouceServerDomain+"/candidate/save";
				 
				 HttpEntity<CandidateDto> httpEntity = new HttpEntity<CandidateDto>(pojo);
				 
				 responseDto =  restTemplate.exchange(url, HttpMethod.POST,httpEntity, ResponseDto.class);
			 }else {
				 
				 url = resouceServerDomain+"/candidate/update";
				 
				 HttpEntity<CandidateDto> httpEntity = new HttpEntity<CandidateDto>(pojo);
				 
				 responseDto =  restTemplate.exchange(url, HttpMethod.PUT,httpEntity, ResponseDto.class);
			 }
			 
			
			 
			 
			 return responseDto;
		}
		
		

		return responseDto;
	}


	public ResponseEntity<ResponseDto> updateAddress(List<AddressDto> addressDtos) {
		
		String url = resouceServerDomain+"/address/saveMultiple";
		
		 HttpEntity<List<AddressDto>> httpEntity = new HttpEntity<List<AddressDto>>(addressDtos);
		 
		 ResponseEntity<ResponseDto> responseDto =  restTemplate.exchange(url, HttpMethod.POST,httpEntity, ResponseDto.class);
		
		 return responseDto;
	}
	
	
	public ResponseEntity<ResponseDto> updateWorkExperience(List<ExperienceEntryDto> ExperienceEntryDtos) {
		
		 HttpEntity<List<ExperienceEntryDto>> httpEntity = new HttpEntity<List<ExperienceEntryDto>>(ExperienceEntryDtos);
		 
		 ResponseEntity<ResponseDto> responseDto =  restTemplate.exchange("http://localhost:9000/resource/experienceEntry/saveMultiple", HttpMethod.POST,httpEntity, ResponseDto.class);
		
		 return responseDto;
	}


	


}
