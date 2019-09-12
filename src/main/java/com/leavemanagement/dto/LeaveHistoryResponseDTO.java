package com.leavemanagement.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LeaveHistoryResponseDTO {

	private int myLeavesId;
	private int employeeId;
	private String status;
	private String leaveType;
	private LocalDate appliedDate;
	private LocalDate forDate;
	private String remark;
}
