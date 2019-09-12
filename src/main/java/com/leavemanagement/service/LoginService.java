package com.leavemanagement.service;

import javax.security.auth.login.LoginException;

import org.springframework.stereotype.Service;

import com.leavemanagement.dto.LoginRequestDto;
import com.leavemanagement.dto.LoginResponseDto;

@Service
public interface LoginService {
	
	public LoginResponseDto userLogin(LoginRequestDto loginRequestDto) throws LoginException;
}
