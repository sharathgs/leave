package com.leavemanagement.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Sushil
 *
 */
@Component
public class ApplyLeaveUtil {

	public static final String EMPLOYEE_DOES_NOT_EXIST_EXCEPTION = "Invalid employee";

	public static final String INSUFFICIENT_LEAVE_EXCEPTION = "Insufficient leave";
	
	public static final String INVALID_DATE_EEXCEPTION = "Invalid leave date";
	
	public static final String RESTRICTED_HOLIDAY = "RH";

	public static final String COMPANY_HOLIDAY = "CH";

	public static final String LEAVE_STATUS_PENDING = "Pending";

	public static final String STATUS_SUCCCESS = "Success";

	public static final String STATUS_FAIL = "Fail";

	public static final String LEAVE_SUCCESS_MESSAGE = "Leave applied successfully";

	public int calculateLeave(String fromDate, String toDate) {
		long noOfDaysBetween = ChronoUnit.DAYS.between(getDateInFormat(fromDate), getDateInFormat(toDate));

		return (int) noOfDaysBetween+1;
	}

	public LocalDate getDateInFormat(String date) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		return LocalDate.parse(date, formatter);
	}

}
