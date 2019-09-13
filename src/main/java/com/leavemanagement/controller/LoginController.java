package com.leavemanagement.controller;


import javax.security.auth.login.LoginException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leavemanagement.dto.LoginRequestDto;
import com.leavemanagement.dto.LoginResponseDto;
import com.leavemanagement.service.LoginService;

@RestController
@RequestMapping("/api")

public class LoginController {
	
	private static final Logger lOGGER = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> userLogin(@RequestBody LoginRequestDto loginRequestDto) throws LoginException{
		lOGGER.info("in login controller");
		return new ResponseEntity<>(loginService.userLogin(loginRequestDto),HttpStatus.OK);
	}
}
