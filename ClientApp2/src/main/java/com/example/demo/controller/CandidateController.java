package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;

import com.example.demo.TokenExpireException;
import com.example.demo.dto.CandidateDto;
import com.example.demo.dto.CandidateFormDto;
import com.example.demo.dto.DocumentDisplayDto;
import com.example.demo.dto.DownloadResource;
import com.example.demo.dto.PagableResponseDto;
import com.example.demo.dto.ResponseDto;
import com.example.demo.dto.SearchDto;
import com.example.demo.service.CandidateService;
import com.example.demo.service.PdfService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping("/candidateService")
public class CandidateController {
	
	


	@Autowired
	private CandidateService candidateService;
	@Autowired
	private PdfService pdfService;
	@Autowired
    private ServletContext servletContext;
	
	@Autowired
	private OAuth2AuthorizedClientService clientService;
	
	@Autowired
	private TemplateEngine templateEngine;

	@InitBinder
	private void dateBinder(WebDataBinder binder) {
		//The date format to parse or output your dates
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		//Create a new CustomDateEditor
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		//Register it as custom editor for the Date type
		binder.registerCustomEditor(Date.class, editor);
	}

	
	
	
	@GetMapping("/search")
	public String openCandidateSearchPage(Model model,Principal principal) {
		
		
		OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());
		
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());
        
        
        
        if(client.getAccessToken().getExpiresAt().compareTo(Instant.now()) > 0) {
        	SearchDto searchDto=new SearchDto();
    		model.addAttribute("searchDto",searchDto);
    		
    		return "candidateSearch";
        }else {
        	throw new TokenExpireException();
        }

		
	}
	
	
	
	
	@PostMapping("/search")
	public String candidateSearch(Model model,Principal principal,@RequestParam(required = false) String locations,
			@RequestParam(required = false) String skillls,
			@RequestParam(required = false)String workExp,
			@RequestParam(required = false)String jobtitles,
			@RequestParam(required = false,defaultValue = "1")Integer page) {

		
		OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());
		
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());
        
        
        
        if(client.getAccessToken().getExpiresAt().compareTo(Instant.now()) > 0) {
        	ResponseEntity<PagableResponseDto> responseDto=candidateService.searchCandidate(locations,skillls,workExp,jobtitles,page);
    		
        	
    		model.addAttribute("currentPage", page);
    		model.addAttribute("candidateDtos",responseDto.getBody().getOutput());
    		model.addAttribute("totalPages", responseDto.getBody().getTotalPages());
    	    model.addAttribute("totalItems", responseDto.getBody().getTotalItems());
    		
    		return "candidateSearch";
        }else {
        	throw new TokenExpireException();
        }

		
		
	}
	
	
	@GetMapping("/search/paginate")
	public String candidateSearchPaginate(Model model,Principal principal,@RequestParam(required = false) String email,
			@RequestParam(required = false) String name,
			@RequestParam(required = false)String workexp,
			@RequestParam(required = true)Integer page) {
		
		OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());
		
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());
        
        
        
        if(client.getAccessToken().getExpiresAt().compareTo(Instant.now()) > 0) {
        	ResponseEntity<PagableResponseDto> responseDto=null;
    		
    		
    		model.addAttribute("currentPage", page);
    		model.addAttribute("candidateDtos",responseDto.getBody().getOutput());
    		model.addAttribute("totalPages", responseDto.getBody().getTotalPages());
    	    model.addAttribute("totalItems", responseDto.getBody().getTotalItems());
    		
    		return "candidateSearch";
        }else {
        	throw new TokenExpireException();
        }

		
	}
	
	
	
	//this method 
	
	@RequestMapping("/profile")
	public String candidateProfile(Model model,Principal principal) {
		
		OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());
		
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());
        
        
        
        if(client.getAccessToken().getExpiresAt().compareTo(Instant.now()) > 0) {
        	CandidateFormDto candidateDto=candidateService.findCandidateByEmail(principal.getName());
    		
    		model.addAttribute("candidateDto",candidateDto);
    		
    		return "view";
        }else {
        	throw new TokenExpireException();
        }
		
		
		
		
		
	}
	
	@GetMapping("/profile/search")
	public String candidateProfileSearch(Model model,Principal principal,@RequestParam(required = true) String email) {
		
		
		OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());
		
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());
        
        
        
        if(client.getAccessToken().getExpiresAt().compareTo(Instant.now()) > 0) {
        	CandidateFormDto candidateDto=candidateService.findCandidateByEmail(email);
    		
    		model.addAttribute("candidateDto",candidateDto);
    		return "view";
        }else {
        	throw new TokenExpireException();
        }
		

		
	}



	@RequestMapping("/saveProfile")
	public String saveCandidateProfile(CandidateDto candidateDto) {
		
		OAuth2AuthenticationToken token = OAuth2AuthenticationToken.class.cast(SecurityContextHolder.getContext().getAuthentication());
		
		OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
				token.getAuthorizedClientRegistrationId(),
				token.getName());
        
        
        
        if(client.getAccessToken().getExpiresAt().compareTo(Instant.now()) > 0) {
        	ResponseEntity<ResponseDto> responseDto=candidateService.saveCandidate(candidateDto);

    		return "candidate";
        }else {
        	throw new TokenExpireException();
        }
			
		
	}
	
	
	@GetMapping("/download-pdf")
    public void downloadPDFResource(@RequestParam(required = true) String email,HttpServletRequest request,HttpServletResponse response) {
       
		
		try {
            Path file = Paths.get(pdfService.generatePdf(email,servletContext,request,response).getAbsolutePath());
            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition",
                        "attachment; filename=" + file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }
        
        
	
	}
	
	
	@RequestMapping(path = "/downloadResume", method = RequestMethod.GET)
	public ResponseEntity<Resource> download(@RequestParam(required = true) String email,
			@RequestParam( required = true) String title,
			HttpServletRequest request,
            HttpServletResponse response) throws IOException {

	   
	   
		 try {
			 
			 ResponseEntity<ResponseDto> responseDto=candidateService.findDocument(email,title);
			 
			 ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				 
			DocumentDisplayDto	documentDisplayDto = mapper.convertValue(responseDto.getBody().getOutput(), DocumentDisplayDto.class);
			 
			
			
			 Resource resource = new ByteArrayResource(Base64.decode(documentDisplayDto.getImage()));
				
				String contentType = null;
		        try {
		            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		        } catch (IOException ex) {
		            //logger.info("Could not determine file type.");
		        }
		        
		        if(contentType == null) {
		            contentType = "application/octet-stream";
		        }
				
				
				return ResponseEntity.ok()
		                .contentType(MediaType.parseMediaType(contentType))
		                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +  documentDisplayDto.getFileName() + "\"")
		                .body( new ByteArrayResource(Base64.decode(documentDisplayDto.getImage())));
				
			 
			 
	        
			
	     }catch (Exception e) {
	           
	            throw new RuntimeException("No such file or directory");
	     }
	}
	

	



}
