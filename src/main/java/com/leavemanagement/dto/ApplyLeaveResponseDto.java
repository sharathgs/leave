package com.leavemanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ApplyLeaveResponseDto {
	
	private String message;
	private int statusCode;
	private String status;

}
