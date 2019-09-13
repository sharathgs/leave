package com.leavemanagement.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leavemanagement.dto.ApplyLeaveRequestDto;
import com.leavemanagement.dto.ApplyLeaveResponseDto;
import com.leavemanagement.service.ApplyLeaveServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ApplyLeaveControllerTest {
	
	@Mock
	ApplyLeaveServiceImpl applyLeaveServiceImpl;
	
	@InjectMocks
	ApplyLeaveController applyLeaveController;
	
	private MockMvc mockMvc;
	
	ApplyLeaveResponseDto responseDto;
	ApplyLeaveRequestDto applyLeaveRequestDto;
	
	@Before
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(applyLeaveController).build();
		responseDto = new ApplyLeaveResponseDto();
		responseDto.setStatus("success");
		responseDto.setStatusCode(201);
		
		applyLeaveRequestDto= new ApplyLeaveRequestDto();
		
		applyLeaveRequestDto.setEmployeeId(1);
		applyLeaveRequestDto.setFromDate("2019-09-12");
		applyLeaveRequestDto.setLeaveType("ch");
		applyLeaveRequestDto.setToDate("2019-09-14");
		applyLeaveRequestDto.setRemark("birthday leave");
	}
	
	@Test
	public void applyLeaveTest() throws JsonProcessingException, Exception 
	{
		Mockito.when(applyLeaveServiceImpl.applyLeave(Mockito.any())).thenReturn(responseDto);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/leaves").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.ALL).content(new ObjectMapper().writeValueAsString(applyLeaveRequestDto))).andReturn();
		
		ResponseEntity<ApplyLeaveResponseDto> response1  = applyLeaveController.applyLeave(applyLeaveRequestDto);
		
		assertEquals(201, response1.getStatusCodeValue());
		assertEquals("success", response1.getBody().getStatus());
	}
	


}
