package com.example.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.CandidateFormDto;
import com.example.demo.dto.DocumentsDto;
import com.example.demo.dto.EducationEntryDto;
import com.example.demo.dto.ExperienceEntryDto;
import com.example.demo.dto.HobbyDto;
import com.example.demo.dto.LanguageDto;
import com.example.demo.dto.MobileDto;
import com.example.demo.dto.PagableResponseDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.SkillDto;
import com.example.demo.dto.SocialProfileDto;
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
		}else if(responseDto.getStatusCode().compareTo(HttpStatus.FORBIDDEN) == 0){
			
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
			pojo.setMaritalStatus("Single");
			pojo.setPlaceOfBirth("NA");
			
			CandidateDto candidateDto = new CandidateDto();
			candidateDto.setFirstName(map.get("firstName"));
			candidateDto.setLastName(map.get("lastName"));
			candidateDto.setEmail(map.get("email"));
			candidateDto.setProfileTitle("NA");
			candidateDto.setSummary("NA");
			candidateDto.setWorkExperience("Fresher");
			candidateDto.setReleventExperience("Fresher");
			candidateDto.setMaritalStatus("Single");
			candidateDto.setPlaceOfBirth("NA");
			
			
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


	public ResponseEntity<PagableResponseDto> searchCandidate(String locations,String skillls,String workExp,String jobtitles,Integer page) {
		String url = resouceServerDomain+"/candidate/search/candidate?locations="+locations+"&skillls="+skillls+"&workExp="+workExp+"&jobtitles="+jobtitles+"&page="+page;
		
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
			 pojo.setPlaceOfBirth(candidateDto.getPlaceOfBirth());
			 pojo.setMaritalStatus(candidateDto.getMaritalStatus());
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
	
	
	public ResponseEntity<ResponseDto> updateWorkExperience(List<ExperienceEntryDto> experienceEntryDtos) {
		
		 HttpEntity<List<ExperienceEntryDto>> httpEntity = new HttpEntity<List<ExperienceEntryDto>>(experienceEntryDtos);
		
		 ResponseEntity<ResponseDto> responseDto =  restTemplate.exchange("http://localhost:9000/resource/experienceEntry/saveMultiple", HttpMethod.POST,httpEntity, ResponseDto.class);
		
		 return responseDto;
	}

	public ResponseEntity<ResponseDto> updateEducations(List<EducationEntryDto> EducationEntryDtos) {
		
		 HttpEntity<List<EducationEntryDto>> httpEntity = new HttpEntity<List<EducationEntryDto>>(EducationEntryDtos);
		
		 ResponseEntity<ResponseDto> responseDto =  restTemplate.exchange("http://localhost:9000/resource/educationEntry/saveMultiple", HttpMethod.POST,httpEntity, ResponseDto.class);
		
		 return responseDto;
	}


	public ResponseEntity<ResponseDto> updateSkills(@Valid List<SkillDto> skillDtos) {

		 HttpEntity<List<SkillDto>> httpEntity = new HttpEntity<List<SkillDto>>(skillDtos);
		
		 ResponseEntity<ResponseDto> responseDto =  restTemplate.exchange("http://localhost:9000/resource/skills/saveMultiple", HttpMethod.POST,httpEntity, ResponseDto.class);
		
		 return responseDto;
	}


	public ResponseEntity<ResponseDto> updateMobiles(@Valid List<MobileDto> mobileDtos) {
		 HttpEntity<List<MobileDto>> httpEntity = new HttpEntity<List<MobileDto>>(mobileDtos);
			
		 ResponseEntity<ResponseDto> responseDto =  restTemplate.exchange("http://localhost:9000/resource/mobile/saveMultiple", HttpMethod.POST,httpEntity, ResponseDto.class);
		
		 return responseDto;
	}


	public ResponseEntity<ResponseDto> updateSocialProfiles(@Valid List<SocialProfileDto> socialProfileDtos) {
		HttpEntity<List<SocialProfileDto>> httpEntity = new HttpEntity<List<SocialProfileDto>>(socialProfileDtos);
		
		 ResponseEntity<ResponseDto> responseDto =  restTemplate.exchange("http://localhost:9000/resource/socialProfile/saveMultiple", HttpMethod.POST,httpEntity, ResponseDto.class);
		
		 return responseDto;
	}


	public ResponseEntity<ResponseDto> updateHobbies(@Valid List<HobbyDto> hobbyDtos) {
		HttpEntity<List<HobbyDto>> httpEntity = new HttpEntity<List<HobbyDto>>(hobbyDtos);
		
		 ResponseEntity<ResponseDto> responseDto =  restTemplate.exchange("http://localhost:9000/resource/hobby/saveMultiple", HttpMethod.POST,httpEntity, ResponseDto.class);
		
		 return responseDto;
	}

	public ResponseEntity<ResponseDto> updateLanguages(@Valid List<LanguageDto> languageDtos) {
		HttpEntity<List<LanguageDto>> httpEntity = new HttpEntity<List<LanguageDto>>(languageDtos);
		
		 ResponseEntity<ResponseDto> responseDto =  restTemplate.exchange("http://localhost:9000/resource/languages/saveMultiple", HttpMethod.POST,httpEntity, ResponseDto.class);
		
		 return responseDto;
	}

	public ResponseEntity<ResponseDto> updateAvatar(DocumentsDto documentsDto) {
		/*
		HttpEntity<DocumentsDto> httpEntity = new HttpEntity<DocumentsDto>(documentsDto);
		
		 ResponseEntity<ResponseDto> responseDto =  restTemplate.exchange("http://localhost:9000/resource/document/saveAvatar", HttpMethod.POST,httpEntity, ResponseDto.class);
		
		 return responseDto;
		 */
		 
		 MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
	     bodyMap.add("file", new FileSystemResource(convert(documentsDto.getImage())));
	     bodyMap.add("email", documentsDto.getCandidateId());
	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	      HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);
	      
	      ResponseEntity<ResponseDto> responseDto = restTemplate.exchange("http://localhost:9000/resource/document/updateAvatar",HttpMethod.POST, requestEntity, ResponseDto.class);
		/*
		 * LinkedMultiValueMap<String,Object> requestEntity = new
		 * LinkedMultiValueMap<>();
		 * 
		 * requestEntity.add("name",srResultEntity.getTitle());
		 * requestEntity.add("files",getUserFileResource());
		 * 
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.set(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_UTF8_VALUE);
		 * headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		 * 
		 * HttpEntity<LinkedMultiValueMap<String,Object>> httpEntity = new
		 * HttpEntity(requestEntity, headers);
		 * 
		 * 
		 * 
		 * final ResponseEntity<ResponseDto> stringResponseEntity =
		 * restTemplate.exchange("http://localhost:9000/resource/document/saveAvatar",
		 * HttpMethod.POST, httpEntity, ResponseDto.class);
		 */
	      
	      return responseDto;
	}
	

	public static File convert(MultipartFile file)
	  {    
	    File convFile = new File(file.getOriginalFilename());
	    try {
	        convFile.createNewFile();
	          FileOutputStream fos = new FileOutputStream(convFile); 
	            fos.write(file.getBytes());
	            fos.close(); 
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } 

	    return convFile;
	 }


	public ResponseEntity<ResponseDto> findAllSkills() {
		String url = resouceServerDomain+"/skills/findAll/SkillName";
		
		@SuppressWarnings("unchecked")
		ResponseEntity<ResponseDto> responseEntity =  restTemplate.exchange(url, HttpMethod.GET, null, ResponseDto.class);

		return responseEntity;
	}


	public ResponseEntity<ResponseDto> findAllWrkExp() {
		String url = resouceServerDomain+"/skills/findAll/SkillName";
		
		@SuppressWarnings("unchecked")
		ResponseEntity<ResponseDto> responseEntity =  restTemplate.exchange(url, HttpMethod.GET, null, ResponseDto.class);

		return responseEntity;
	}


	public ResponseEntity<ResponseDto> findAllCountries() {
		String url = resouceServerDomain+"/address/findAll/countryName";
		
		@SuppressWarnings("unchecked")
		ResponseEntity<ResponseDto> responseEntity =  restTemplate.exchange(url, HttpMethod.GET, null, ResponseDto.class);

		return responseEntity;
	}


	public ResponseEntity<ResponseDto> findAllWorkExp() {
		String url = resouceServerDomain+"/candidate/findAll/workExp";
		
		@SuppressWarnings("unchecked")
		ResponseEntity<ResponseDto> responseEntity =  restTemplate.exchange(url, HttpMethod.GET, null, ResponseDto.class);

		return responseEntity;
	}
	public ResponseEntity<ResponseDto> findAllMaritalStatus() {
		String url = resouceServerDomain+"/candidate/findAll/maritalStatus";
		
		@SuppressWarnings("unchecked")
		ResponseEntity<ResponseDto> responseEntity =  restTemplate.exchange(url, HttpMethod.GET, null, ResponseDto.class);

		return responseEntity;
	}
	
	public ResponseEntity<ResponseDto> findAllSchools() {
		String url = resouceServerDomain+"/candidate/findAll/schools";
		
		@SuppressWarnings("unchecked")
		ResponseEntity<ResponseDto> responseEntity =  restTemplate.exchange(url, HttpMethod.GET, null, ResponseDto.class);

		return responseEntity;
	}
	public ResponseEntity<ResponseDto> findAllDegree() {
		String url = resouceServerDomain+"/candidate/findAll/degree";
		
		@SuppressWarnings("unchecked")
		ResponseEntity<ResponseDto> responseEntity =  restTemplate.exchange(url, HttpMethod.GET, null, ResponseDto.class);

		return responseEntity;
	}
	
	public ResponseEntity<ResponseDto> findAllLanguage() {
		String url = resouceServerDomain+"/candidate/findAll/language";
		
		@SuppressWarnings("unchecked")
		ResponseEntity<ResponseDto> responseEntity =  restTemplate.exchange(url, HttpMethod.GET, null, ResponseDto.class);

		return responseEntity;
	}
	public ResponseEntity<ResponseDto> findAllCompanies() {
		String url = resouceServerDomain+"/candidate/findAll/companies";
		
		@SuppressWarnings("unchecked")
		ResponseEntity<ResponseDto> responseEntity =  restTemplate.exchange(url, HttpMethod.GET, null, ResponseDto.class);

		return responseEntity;
	}
	public ResponseEntity<ResponseDto> findAllIndustries() {
		String url = resouceServerDomain+"/candidate/findAll/industries";
		
		@SuppressWarnings("unchecked")
		ResponseEntity<ResponseDto> responseEntity =  restTemplate.exchange(url, HttpMethod.GET, null, ResponseDto.class);

		return responseEntity;
	}

	public ResponseEntity<ResponseDto> updateResume(DocumentsDto documentsDto) {
		MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
	     bodyMap.add("file", new FileSystemResource(convert(documentsDto.getImage())));
	     bodyMap.add("email", documentsDto.getCandidateId());
	      HttpHeaders headers = new HttpHeaders();
	      headers.setContentType(MediaType.MULTIPART_FORM_DATA);
	      HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);
	      
	      ResponseEntity<ResponseDto> responseDto = restTemplate.exchange("http://localhost:9000/resource/document/updateResume",HttpMethod.POST, requestEntity, ResponseDto.class);
	
	      return responseDto;
	}


	public ResponseEntity<ResponseDto> findDocument(String email, String title) {
		String url = resouceServerDomain+"/document/find/document/"+email+"/"+title;
		
		@SuppressWarnings("unchecked")
		ResponseEntity<ResponseDto> responseEntity =  restTemplate.exchange(url, HttpMethod.GET, null, ResponseDto.class);

		return responseEntity;
	}


	public ResponseEntity<ResponseDto> deleteDocument(String email, String title) {
		String url = resouceServerDomain+"/document/delete/document/"+email+"/"+title;
		
		@SuppressWarnings("unchecked")
		ResponseEntity<ResponseDto> responseEntity =  restTemplate.exchange(url, HttpMethod.GET, null, ResponseDto.class);

		return responseEntity;
	}
}
