package com.leavemanagement.service;


	

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.leavemanagement.dto.LoginRequestDto;
import com.leavemanagement.dto.LoginResponseDto;
import com.leavemanagement.exception.InvalidLoginException;
import com.leavemanagement.model.Employee;
import com.leavemanagement.repository.LoginRepository;
import com.leavemanagement.util.LeaveUtil;
import com.leavemanagement.util.Status;

@Service
public class LoginServiceImpl implements LoginService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	LoginRepository loginRepository;

	@Override
	public LoginResponseDto userLogin(LoginRequestDto loginRequestDto)  {
	
		logger.info("in userlogin service");
		String userEmail = loginRequestDto.getEmail();
		if (userEmail.length() <= 8)
			throw new InvalidLoginException(LeaveUtil.INVALID_DOMAIN);
		String emailName = userEmail.substring(0, (userEmail.length() - 1) - 7);
		String emailDomain = userEmail.substring(emailName.length(), userEmail.length());
		
		if (!emailDomain.equals("@hcl.com"))
			throw new InvalidLoginException(LeaveUtil.INVALID_DOMAIN);

		LoginResponseDto loginResponseDto = new LoginResponseDto();
		Optional<Employee> employee = loginRepository.findByEmail(userEmail);

		if (!employee.isPresent())
			throw new InvalidLoginException(LeaveUtil.USER_NOT_FOUND);

		if (employee.get().getEmail().equals(loginRequestDto.getEmail())
				&& employee.get().getPassword().equals(loginRequestDto.getPassword())) {
			loginResponseDto.setMessage(employee.get().getName() + LeaveUtil.USER_FOUND);
			loginResponseDto.setStatus(Status.SUCCESS);
			loginResponseDto.setStatusCode(HttpStatus.OK.value());
			loginResponseDto.setEmployeeId(employee.get().getEmployeeId());
			loginResponseDto.setName(employee.get().getName());
		} else {
			loginResponseDto.setMessage(LeaveUtil.INVALID_DETAILS);
			loginResponseDto.setStatus(Status.FAILURE);
			loginResponseDto.setStatusCode(HttpStatus.NOT_FOUND.value());
		}

		return loginResponseDto;
	}

}

