package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.TokenExpireException;

@Controller
public class AccessDeniedController {

    @GetMapping("/access-denied")
    public String getAccessDenied() {
        return "error/accessDenied";
    }
    
    
    @ExceptionHandler(TokenExpireException.class)
    public String handleUnknownIdentifierException(final HttpServletRequest request, final HttpServletResponse response)
    {  
        return "redirect:notfoundpage";
    }
    
    
    
}
