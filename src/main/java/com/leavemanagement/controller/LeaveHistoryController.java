package com.leavemanagement.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leavemanagement.dto.LeaveHistoryResponseDTO;
import com.leavemanagement.service.LeaveHistoryService;

@RequestMapping("/api")
@RestController
@CrossOrigin(allowedHeaders = { "*", "/" }, origins = { "*", "/" })

public class LeaveHistoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LeaveHistoryController.class);

	@Autowired
	private LeaveHistoryService leaveHistoryService;

	/**
	 * This method is use to get leave history based on date or month
	 * @param employeeId,not null
	 * @param months,not null
	 * @param fromDate,optional
	 * @param toDate,optional
	 * @return ResponseEntity<List<LeaveHistoryResponseDTO>> ,not null
	 */

	@GetMapping("/leaves/history/{employeeId}")
	public ResponseEntity<List<LeaveHistoryResponseDTO>> getAllLeaveHistory(@PathVariable int employeeId,
			@RequestParam(required = false) int months, @RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate) {

		LOGGER.info("inside getAllLeaveHistory method of LeaveHistoryController class");

		List<LeaveHistoryResponseDTO> list = leaveHistoryService.getAllLeaveHistory(employeeId, months, fromDate,
				toDate);

		return new ResponseEntity<>(list, HttpStatus.OK);

	}
}
