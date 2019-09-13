package com.leavemanagement.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.leavemanagement.dto.ApplyLeaveRequestDto;
import com.leavemanagement.dto.ApplyLeaveResponseDto;
import com.leavemanagement.exception.EmployeeDoesNotExistException;
import com.leavemanagement.exception.InsufficientLeaveException;
import com.leavemanagement.exception.InvalidDateException;
import com.leavemanagement.model.Leaves;
import com.leavemanagement.model.MyLeaves;
import com.leavemanagement.repository.LeaveHistoryRepository;
import com.leavemanagement.repository.LeaveRepository;
import com.leavemanagement.util.ApplyLeaveUtil;

/**
 * 
 * @author Sushil
 *
 */
@Service
public class ApplyLeaveServiceImpl implements ApplyLeaveService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplyLeaveServiceImpl.class);
	@Autowired
	LeaveRepository leaveRepository;

	@Autowired
	LeaveHistoryRepository myLeavesRepository;

	@Autowired
	ApplyLeaveUtil applyLeaveUtil;

	/**
	 * This method is use to apply leave for employee
	 * 
	 * @param applyLeaveRequestDto,not null
	 * @return ApplyLeaveResponseDto ,not null
	 */
	@Override
	public ApplyLeaveResponseDto applyLeave(ApplyLeaveRequestDto applyLeaveRequestDto) {
		LOGGER.info("Inside applyLeave method of ApplyLeaveServiceImpl class");

		ApplyLeaveResponseDto responseDto = new ApplyLeaveResponseDto();

		if (applyLeaveUtil.getDateInFormat(applyLeaveRequestDto.getFromDate()).compareTo(LocalDate.now()) < 0
				|| (applyLeaveUtil.getDateInFormat(applyLeaveRequestDto.getToDate()).compareTo(applyLeaveUtil.getDateInFormat(applyLeaveRequestDto.getFromDate())) < 0)) {
					
			throw new InvalidDateException(ApplyLeaveUtil.INVALID_DATE_EEXCEPTION);

		} else {
			Optional<Leaves> leave = leaveRepository.findByemployeeId(applyLeaveRequestDto.getEmployeeId());

			/* create ApplyLeaveResponseDto object */

			if (leave.isPresent()) {
				List<MyLeaves> myLeavesList = new ArrayList<>();
				int noOfLeaves = applyLeaveUtil.calculateLeave(applyLeaveRequestDto.getFromDate(),
						applyLeaveRequestDto.getToDate());
				if (applyLeaveRequestDto.getLeaveType().equalsIgnoreCase(ApplyLeaveUtil.COMPANY_HOLIDAY)
						&& leave.get().getCompanyHoliday() >= noOfLeaves) {
					leave.get().setCompanyHoliday(leave.get().getCompanyHoliday() - noOfLeaves);
					leave.get().setAvailedCompanyHoliday(leave.get().getAvailedCompanyHoliday() + noOfLeaves);
				} else if (applyLeaveRequestDto.getLeaveType().equalsIgnoreCase(ApplyLeaveUtil.RESTRICTED_HOLIDAY)
						&& leave.get().getRestrictedHoliday() >= noOfLeaves) {
					leave.get().setRestrictedHoliday(leave.get().getRestrictedHoliday() - noOfLeaves);
					leave.get().setAvailedRestrictedHoliday(leave.get().getAvailedRestrictedHoliday() + noOfLeaves);
				} else {
					throw new InsufficientLeaveException(ApplyLeaveUtil.INSUFFICIENT_LEAVE_EXCEPTION);
				}
				LocalDate appliedLocalDate = applyLeaveUtil.getDateInFormat(applyLeaveRequestDto.getFromDate());
				LocalDate currentLocalDate = LocalDate.now();
				for (int day = 0; day < noOfLeaves; day++) {
					MyLeaves myLeaves = new MyLeaves();
					myLeaves.setEmployeeId(applyLeaveRequestDto.getEmployeeId());
					myLeaves.setLeaveType(applyLeaveRequestDto.getLeaveType());
					myLeaves.setStatus(ApplyLeaveUtil.LEAVE_STATUS_PENDING);
					myLeaves.setForDate(appliedLocalDate.plusDays(day));
					myLeaves.setAppliedDate(currentLocalDate);
					myLeaves.setRemark(applyLeaveRequestDto.getRemark());
					myLeavesList.add(myLeaves);
				}
				leaveRepository.save(leave.get());
				myLeavesRepository.saveAll(myLeavesList);

				responseDto.setStatus(ApplyLeaveUtil.STATUS_SUCCCESS);
				responseDto.setStatusCode(HttpStatus.CREATED.value());
				responseDto.setMessage(ApplyLeaveUtil.LEAVE_SUCCESS_MESSAGE);

			} else {
				throw new EmployeeDoesNotExistException(ApplyLeaveUtil.EMPLOYEE_DOES_NOT_EXIST_EXCEPTION);
			}
		}
		return responseDto;

	}

}
