package com.example.demo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.ResponseDto;

@FeignClient(name = "test", url = "http://localhost:9000",configuration = FeignClientConfig.class)
public interface  CandidateClient {
	
	@RequestMapping(value = "resource/candidate/findAll",produces = "application/json",consumes = "application/json;charset=UTF-8",method = RequestMethod.GET)
	ResponseEntity<ResponseDto> getCandidates();

}
