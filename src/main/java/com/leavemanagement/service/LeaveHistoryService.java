package com.leavemanagement.service;

import java.util.List;

import com.leavemanagement.dto.LeaveHistoryResponseDTO;

public interface LeaveHistoryService {

	List<LeaveHistoryResponseDTO> getAllLeaveHistory(int employeeId, int months, String fromDate, String toDate);

}
