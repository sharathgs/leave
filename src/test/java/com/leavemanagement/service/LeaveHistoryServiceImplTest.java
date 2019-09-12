package com.leavemanagement.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.leavemanagement.dto.LeaveHistoryResponseDTO;
import com.leavemanagement.model.MyLeaves;
import com.leavemanagement.repository.LeaveHistoryRepository;

@RunWith(MockitoJUnitRunner.class)
public class LeaveHistoryServiceImplTest {

	@InjectMocks
	LeaveHistoryServiceImpl leaveHistoryServiceImpl;

	@Mock
	LeaveHistoryRepository leaveHistoryRepository;

	public List<MyLeaves> getAllLeaveHistoryTest() {
		List<MyLeaves> leaves = new ArrayList<>();
		MyLeaves myLeaves2 = new MyLeaves();

		myLeaves2.setAppliedDate(LocalDate.now());
		myLeaves2.setEmployeeId(1);
		myLeaves2.setFromDate(LocalDate.now());
		myLeaves2.setLeaveType("CH");
		myLeaves2.setNoOfDays(2);
		myLeaves2.setMyLeavesId(1);
		myLeaves2.setStatus("Approved");
		myLeaves2.setToDate(LocalDate.now());
		leaves.add(myLeaves2);
		return leaves;
	}

	public List<LeaveHistoryResponseDTO> getAllLeaveHistorys() {
		List<LeaveHistoryResponseDTO> historyResponseDTOs = new ArrayList<>();

		LeaveHistoryResponseDTO myLeaves = new LeaveHistoryResponseDTO();
		myLeaves.setAppliedDate(LocalDate.now());
		myLeaves.setEmployeeId(1);
		myLeaves.setFromDate(LocalDate.now());
		myLeaves.setLeaveType("CH");
		myLeaves.setNoOfDays(2);
		myLeaves.setMyLeavesId(1);
		myLeaves.setStatus("Approved");
		myLeaves.setToDate(LocalDate.now());
		historyResponseDTOs.add(myLeaves);
		return historyResponseDTOs;

	}

	@Test
	public void getAllLeaveHistory() {
		Mockito.when(leaveHistoryRepository.findByBetweenDate(Mockito.anyInt(), Mockito.any(), Mockito.any()))
				.thenReturn((getAllLeaveHistoryTest()));
		List<LeaveHistoryResponseDTO> result = leaveHistoryServiceImpl.getAllLeaveHistory(1, 2, "2019-08-11",
				"2019-08-18");
		Assert.assertEquals(1, result.size());
	}

}
