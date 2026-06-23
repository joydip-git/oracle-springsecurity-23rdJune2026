package com.security.basicapp.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.security.basicapp.BasicappApplication;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    private final BasicappApplication basicappApplication;

    HelloController(BasicappApplication basicappApplication) {
        this.basicappApplication = basicappApplication;
    }

	@GetMapping("/")
	public String hello(HttpServletRequest request) {
		return "Hello from app..." + request.getSession().getId();
	}
	
	@GetMapping("/csrf-token")
	public CsrfToken getCsrfToken(HttpServletRequest request) {
		return (CsrfToken)request.getAttribute("_csrf");
	}

}
