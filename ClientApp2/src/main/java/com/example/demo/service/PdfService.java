package com.example.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

@Service
public class PdfService {

    private static final String PDF_RESOURCES = "/pdf-resources/";
    private CandidateService candidateService;
    private SpringTemplateEngine templateEngine;

    @Autowired
    public PdfService(CandidateService candidateService, SpringTemplateEngine templateEngine) {
        this.candidateService = candidateService;
        this.templateEngine = templateEngine;
    }

    
    public File generatePdf(String email,ServletContext servletContext,HttpServletRequest request,HttpServletResponse response) throws IOException, DocumentException {
    	WebContext context = new WebContext(request, response, servletContext);
    	 //Context context = new Context();
         context.setVariable("candidateDto", candidateService.findCandidateByEmail(email));
         context.setVariable("baseUrl", getCurrentBaseUrl());
       
        String html = loadAndFillTemplate(context);
        return renderPdf(html);
    }


    private File renderPdf(String html) throws IOException, DocumentException {
        File file = File.createTempFile("candidate", ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html, new ClassPathResource("/").getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }

 
    private String loadAndFillTemplate(WebContext context) {
        return templateEngine.process("view_pdf", context);
    }

    
	private static String getCurrentBaseUrl() {
		ServletRequestAttributes sra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest req = sra.getRequest();
		return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
		} 

}
