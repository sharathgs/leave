package com.leavemanagement.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leavemanagement.dto.LeaveHistoryResponseDTO;
import com.leavemanagement.model.MyLeaves;
import com.leavemanagement.repository.LeaveHistoryRepository;

@Service
public class LeaveHistoryServiceImpl implements LeaveHistoryService {

	@Autowired
	LeaveHistoryRepository leaveHistoryRepository;

	public List<LeaveHistoryResponseDTO> getAllLeaveHistory(int employeeId, int months, String fromDate,
			String toDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fromLocalDate = null;
		LocalDate toLocalDate = null;
		List<LeaveHistoryResponseDTO> leaveHistoryResponse = new ArrayList<>();
		List<MyLeaves> myLeaves = null;
		if (fromDate != null && toDate != null) {
			fromLocalDate = LocalDate.parse(fromDate, formatter);
			toLocalDate = LocalDate.parse(toDate, formatter);
		} else {
			fromLocalDate = LocalDate.now().minusMonths(months);
			toLocalDate = LocalDate.now();
		}
		myLeaves = leaveHistoryRepository.findByBetweenDate(employeeId, fromLocalDate, toLocalDate);

		if (!myLeaves.isEmpty()) {
			myLeaves.stream().forEach(myleave -> {
				LeaveHistoryResponseDTO leavesDto = new LeaveHistoryResponseDTO();
				BeanUtils.copyProperties(myleave, leavesDto);
				leaveHistoryResponse.add(leavesDto);
			});
		}

		return leaveHistoryResponse;
	}

}
