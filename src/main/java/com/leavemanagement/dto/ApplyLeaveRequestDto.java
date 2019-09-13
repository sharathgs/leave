package com.leavemanagement.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * 
 * @author Sushil
 *
 */
@Setter
@Getter
@NoArgsConstructor
public class ApplyLeaveRequestDto {
	
	private int employeeId;
	private String fromDate;
	private String toDate;
	private String leaveType;
	private String remark;

}
