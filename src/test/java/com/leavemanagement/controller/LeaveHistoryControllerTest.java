package com.leavemanagement.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.leavemanagement.dto.LeaveHistoryResponseDTO;
import com.leavemanagement.service.LeaveHistoryService;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class LeaveHistoryControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	LeaveHistoryController leaveHistoryController;

	@Mock
	LeaveHistoryService leaveHistoryService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(leaveHistoryController).build();
	}

	public List<LeaveHistoryResponseDTO> getAllLeaveHistoryTest() {
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
	public void getLeaveHistoryController() {
		ResponseEntity<List<LeaveHistoryResponseDTO>> expResult = new ResponseEntity<>(getAllLeaveHistoryTest(),
				HttpStatus.OK);
		when(leaveHistoryService.getAllLeaveHistory(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString(),
				Mockito.anyString())).thenReturn(getAllLeaveHistoryTest());
		ResponseEntity<List<LeaveHistoryResponseDTO>> actResult = leaveHistoryController.getAllLeaveHistory(1,
				2, "2019-08-12", "2019-08-16");
		assertEquals(expResult.getStatusCode(), actResult.getStatusCode());
	}

}
