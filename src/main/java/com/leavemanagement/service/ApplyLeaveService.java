package com.leavemanagement.service;

import com.leavemanagement.dto.ApplyLeaveRequestDto;
import com.leavemanagement.dto.ApplyLeaveResponseDto;

/**
 * 
 * @author Sushil
 *
 */
public interface ApplyLeaveService {
	
	public ApplyLeaveResponseDto applyLeave(ApplyLeaveRequestDto applyLeaveRequestDto);

}
