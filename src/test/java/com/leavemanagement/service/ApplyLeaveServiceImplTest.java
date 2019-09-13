package com.leavemanagement.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.leavemanagement.dto.ApplyLeaveRequestDto;
import com.leavemanagement.dto.ApplyLeaveResponseDto;
import com.leavemanagement.model.Leaves;
import com.leavemanagement.model.MyLeaves;
import com.leavemanagement.repository.LeaveHistoryRepository;
import com.leavemanagement.repository.LeaveRepository;
import com.leavemanagement.util.ApplyLeaveUtil;

@RunWith(MockitoJUnitRunner.class)
public class ApplyLeaveServiceImplTest {

	@Mock
	LeaveRepository leaveRepository;

	@Mock
	LeaveHistoryRepository leaveHistoryRepository;
	
	@Mock
	ApplyLeaveUtil applyLeaveUtil;
	
	@InjectMocks
	ApplyLeaveServiceImpl applyLeaveServiceImpl;

	ApplyLeaveRequestDto applyLeaveRequestDto;
	
	List<MyLeaves> myLeavesList = new ArrayList<>();

	Leaves leaves;
	LocalDate localdate;
	LocalDate toDate;

	@Before
	public void init() {

		applyLeaveRequestDto = new ApplyLeaveRequestDto();

		applyLeaveRequestDto.setEmployeeId(1);
		applyLeaveRequestDto.setFromDate("2019-09-12");
		applyLeaveRequestDto.setLeaveType("ch");
		applyLeaveRequestDto.setToDate("2019-09-14");
		applyLeaveRequestDto.setRemark("birthday leave");

		leaves = new Leaves();
		leaves.setAvailedCompanyHoliday(0);
		leaves.setAvailedRestrictedHoliday(0);
		leaves.setCompanyHoliday(14);
		leaves.setEmployeeId(1);
		leaves.setLeavesId(101);
		leaves.setRestrictedHoliday(8);
		
		localdate = LocalDate.now().plusDays(10);
		toDate = LocalDate.now().plusDays(20);
	}

	@Test
	public void applyLeaveTest() {
		Mockito.when(leaveRepository.findByemployeeId(Mockito.anyInt())).thenReturn(Optional.of(leaves));
		Mockito.when(applyLeaveUtil.getDateInFormat(applyLeaveRequestDto.getFromDate())).thenReturn(localdate); 
		Mockito.when(applyLeaveUtil.getDateInFormat(applyLeaveRequestDto.getToDate())).thenReturn(toDate);
		Mockito.when(leaveRepository.save(Mockito.any())).thenReturn(leaves);
		Mockito.when(leaveHistoryRepository.saveAll(Mockito.anyList())).thenReturn(myLeavesList);
		
		ApplyLeaveResponseDto acctualResult = applyLeaveServiceImpl.applyLeave(applyLeaveRequestDto);
		assertEquals(ApplyLeaveUtil.STATUS_SUCCCESS, acctualResult.getStatus());
	}
}
