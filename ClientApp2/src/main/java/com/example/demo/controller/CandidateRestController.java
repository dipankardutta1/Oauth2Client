package com.example.demo.controller;

import java.security.Principal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.TokenExpireException;
import com.example.demo.TokenExpireJsonException;
import com.example.demo.dto.AddressDto;
import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.CandidateFormDto;
import com.example.demo.dto.EducationEntryDto;
import com.example.demo.dto.ExperienceEntryDto;
import com.example.demo.dto.HobbyDto;
import com.example.demo.dto.Mobile;
import com.example.demo.dto.MobileDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.SkillDto;
import com.example.demo.dto.Skills;
import com.example.demo.dto.SocialProfileDto;
import com.example.demo.dto.SocialProfiles;
import com.example.demo.service.CandidateService;

@RestController
public class CandidateRestController {

	@Autowired
	private CandidateService candidateService;
	@Autowired
	private OAuth2AuthorizedClientService clientService;


	@PostMapping("/api/candidate/summary/update")
	public ResponseEntity<?> saveCandidateSummary(Principal principal, @Valid @RequestBody CandidateDto candidateDto, Errors errors) {
		
		OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());
		
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());
        
        
        
        if(client.getAccessToken().getExpiresAt().compareTo(Instant.now()) > 0) {
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
        }else {
        	//throw new TokenExpireJsonException();
        	ResponseEntity<ResponseDto> responseDto=new ResponseEntity<ResponseDto>(HttpStatus.UNAUTHORIZED);
        	
        	return responseDto;
        	
        }
		
		
		
	}



	@PostMapping("/api/candidate/profile/update")
	public ResponseEntity<?> saveCandidateProfile(Principal principal, @Valid @RequestBody CandidateDto candidateDto, Errors errors) {

		OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());
		
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());
        
        
        
        if(client.getAccessToken().getExpiresAt().compareTo(Instant.now()) > 0) {
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
        }else {
        	//throw new TokenExpireJsonException();
        	ResponseEntity<ResponseDto> responseDto=new ResponseEntity<ResponseDto>(HttpStatus.UNAUTHORIZED);
        	
        	return responseDto;
        	
        }



		
	}



	@PostMapping("/api/candidate/address/update")
	public ResponseEntity<?> saveCandidateAddress(Principal principal, @Valid @RequestBody List<AddressDto> addressDtos, Errors errors) {
		
		OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());
		
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());
        
        
        
        if(client.getAccessToken().getExpiresAt().compareTo(Instant.now()) > 0) {
        	if (errors.hasErrors()) {
    			ResponseDto responseDto = new ResponseDto();

    			responseDto.setMsg(errors.getAllErrors()
    					.stream().map(x -> x.getDefaultMessage())
    					.collect(Collectors.joining(",")));

    			return ResponseEntity.badRequest().body(responseDto);

    		}

    		addressDtos.forEach((addressDto)->{
    			addressDto.setCandidateId(principal.getName());
    		});

    		ResponseEntity<ResponseDto> responseDto=candidateService.updateAddress(addressDtos);
    		responseDto.getBody().setOutput(addressDtos);

    		return responseDto;
        }else {
        	//throw new TokenExpireJsonException();
        	ResponseEntity<ResponseDto> responseDto=new ResponseEntity<ResponseDto>(HttpStatus.UNAUTHORIZED);
        	
        	return responseDto;
        	
        }
		
		
		
		
		
	}


	@PostMapping("/api/candidate/workExp/update")
	public ResponseEntity<?> saveCandidateWorkExperiences(Principal principal, @Valid @RequestBody List<ExperienceEntryDto> experienceEntryDtos, Errors errors) {
		
		
		OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());
		
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());
        
        
        
        if(client.getAccessToken().getExpiresAt().compareTo(Instant.now()) > 0) {
        	if (errors.hasErrors()) {
    			ResponseDto responseDto = new ResponseDto();

    			responseDto.setMsg(errors.getAllErrors()
    					.stream().map(x -> x.getDefaultMessage())
    					.collect(Collectors.joining(",")));

    			return ResponseEntity.badRequest().body(responseDto);

    		}

    		experienceEntryDtos.forEach((experienceEntryDto)->{
    			experienceEntryDto.setCandidateId(principal.getName());
    		});

    		ResponseEntity<ResponseDto> responseDto=candidateService.updateWorkExperience(experienceEntryDtos);
    		responseDto.getBody().setOutput(experienceEntryDtos);

    		return responseDto;
        }else {
        	//throw new TokenExpireJsonException();
        	ResponseEntity<ResponseDto> responseDto=new ResponseEntity<ResponseDto>(HttpStatus.UNAUTHORIZED);
        	
        	return responseDto;
        	
        }
		
		
		
	}

	@PostMapping("/api/candidate/education/update")
	public ResponseEntity<?> saveCandidateEducations(Principal principal, @Valid @RequestBody List<EducationEntryDto> EducationEntryDtos, Errors errors) {
		
		
		OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());
		
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());
        
        
        
        if(client.getAccessToken().getExpiresAt().compareTo(Instant.now()) > 0) {
        	if (errors.hasErrors()) {
    			ResponseDto responseDto = new ResponseDto();

    			responseDto.setMsg(errors.getAllErrors()
    					.stream().map(x -> x.getDefaultMessage())
    					.collect(Collectors.joining(",")));

    			return ResponseEntity.badRequest().body(responseDto);

    		}

    		EducationEntryDtos.forEach((EducationEntryDto)->{
    			EducationEntryDto.setCandidateId(principal.getName());
    		});

    		ResponseEntity<ResponseDto> responseDto=candidateService.updateEducations(EducationEntryDtos);
    		responseDto.getBody().setOutput(EducationEntryDtos);

    		return responseDto;
        }else {
        	//throw new TokenExpireJsonException();
        	ResponseEntity<ResponseDto> responseDto=new ResponseEntity<ResponseDto>(HttpStatus.UNAUTHORIZED);
        	
        	return responseDto;
        	
        }
		
		
		
		
		
		
	}
	
	
	@PostMapping("/api/candidate/skill/update")
	public ResponseEntity<?> saveCandidateSkills(Principal principal, @Valid @RequestBody List<SkillDto> skillDtos, Errors errors) {
		OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());
		
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());
        
        
        
        if(client.getAccessToken().getExpiresAt().compareTo(Instant.now()) > 0) {
        	if (errors.hasErrors()) {
    			ResponseDto responseDto = new ResponseDto();

    			responseDto.setMsg(errors.getAllErrors()
    					.stream().map(x -> x.getDefaultMessage())
    					.collect(Collectors.joining(",")));

    			return ResponseEntity.badRequest().body(responseDto);

    		}

    		skillDtos.forEach((skillDto)->{
    			skillDto.setCandidateId(principal.getName());
    		});

    		ResponseEntity<ResponseDto> responseDto=candidateService.updateSkills(skillDtos);
    		responseDto.getBody().setOutput(skillDtos);

    		return responseDto;
        }else {
        	//throw new TokenExpireJsonException();
        	ResponseEntity<ResponseDto> responseDto=new ResponseEntity<ResponseDto>(HttpStatus.UNAUTHORIZED);
        	
        	return responseDto;
        	
        }
		
		
		
		
		
	}
	
	
	@PostMapping("/api/candidate/mobile/update")
	public ResponseEntity<?> saveCandidateMobiles(Principal principal, @Valid @RequestBody List<MobileDto> mobileDtos, Errors errors) {
		OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());
		
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());
        
        
        
        if(client.getAccessToken().getExpiresAt().compareTo(Instant.now()) > 0) {
        	if (errors.hasErrors()) {
    			ResponseDto responseDto = new ResponseDto();

    			responseDto.setMsg(errors.getAllErrors()
    					.stream().map(x -> x.getDefaultMessage())
    					.collect(Collectors.joining(",")));

    			return ResponseEntity.badRequest().body(responseDto);

    		}

    		mobileDtos.forEach((mobileDto)->{
    			mobileDto.setCandidateId(principal.getName());
    		});

    		ResponseEntity<ResponseDto> responseDto=candidateService.updateMobiles(mobileDtos);
    		responseDto.getBody().setOutput(mobileDtos);

    		return responseDto;
        }else {
        	//throw new TokenExpireJsonException();
        	ResponseEntity<ResponseDto> responseDto=new ResponseEntity<ResponseDto>(HttpStatus.UNAUTHORIZED);
        	
        	return responseDto;
        	
        }
		
		
	}
	
	
	
	@PostMapping("/api/candidate/social/update")
	public ResponseEntity<?> saveCandidateSocialProfiles(Principal principal, @Valid @RequestBody List<SocialProfileDto> socialProfileDtos, Errors errors) {
		
		OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());
		
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());
        
        
        
        if(client.getAccessToken().getExpiresAt().compareTo(Instant.now()) > 0) {
        	if (errors.hasErrors()) {
    			ResponseDto responseDto = new ResponseDto();

    			responseDto.setMsg(errors.getAllErrors()
    					.stream().map(x -> x.getDefaultMessage())
    					.collect(Collectors.joining(",")));

    			return ResponseEntity.badRequest().body(responseDto);

    		}

        	socialProfileDtos.forEach((socialProfileDto)->{
        		socialProfileDto.setCandidateId(principal.getName());
    		});

    		ResponseEntity<ResponseDto> responseDto=candidateService.updateSocialProfiles(socialProfileDtos);
    		responseDto.getBody().setOutput(socialProfileDtos);

    		return responseDto;
        }else {
        	//throw new TokenExpireJsonException();
        	ResponseEntity<ResponseDto> responseDto=new ResponseEntity<ResponseDto>(HttpStatus.UNAUTHORIZED);
        	
        	return responseDto;
        	
        }
		
		
	}
	
	
	@PostMapping("/api/candidate/hobby/update")
	public ResponseEntity<?> saveCandidateHobbies(Principal principal, @Valid @RequestBody List<HobbyDto> hobbyDtos, Errors errors) {
		
		OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());
		
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());
        
        
        
        if(client.getAccessToken().getExpiresAt().compareTo(Instant.now()) > 0) {
        	if (errors.hasErrors()) {
    			ResponseDto responseDto = new ResponseDto();

    			responseDto.setMsg(errors.getAllErrors()
    					.stream().map(x -> x.getDefaultMessage())
    					.collect(Collectors.joining(",")));

    			return ResponseEntity.badRequest().body(responseDto);

    		}

        	hobbyDtos.forEach((hobby)->{
        		hobby.setCandidateId(principal.getName());
    		});

    		ResponseEntity<ResponseDto> responseDto=candidateService.updateHobbies(hobbyDtos);
    		responseDto.getBody().setOutput(hobbyDtos);

    		return responseDto;
        }else {
        	//throw new TokenExpireJsonException();
        	ResponseEntity<ResponseDto> responseDto=new ResponseEntity<ResponseDto>(HttpStatus.UNAUTHORIZED);
        	
        	return responseDto;
        	
        }
		
		
	}

}
