package com.leavemanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leavemanagement.dto.ApplyLeaveRequestDto;
import com.leavemanagement.dto.ApplyLeaveResponseDto;
import com.leavemanagement.service.ApplyLeaveService;

/**
 * 
 * @author Sushil
 *
 */
@RequestMapping("/api")
@RestController
public class ApplyLeaveController {
	
	private static final Logger LOGGER  =LoggerFactory.getLogger(ApplyLeaveController.class);
	@Autowired
	ApplyLeaveService applyLeaveService;
	/**
	 * This method is use apply leave 
	 * @param applyLeaveRequestDto,not null
	 * @return ResponseEntity<ApplyLeaveResponseDto> ,not null
	 */
	@PostMapping("/leaves")
	public ResponseEntity<ApplyLeaveResponseDto> applyLeave(@RequestBody ApplyLeaveRequestDto applyLeaveRequestDto)
	{
		LOGGER.info("Inside applyLeave method of ApplyLeaveController class");
		ApplyLeaveResponseDto response = applyLeaveService.applyLeave(applyLeaveRequestDto);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
