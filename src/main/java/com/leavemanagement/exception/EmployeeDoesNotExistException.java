package com.leavemanagement.exception;
/**
 * 
 * @author Sushil
 *
 */
public class EmployeeDoesNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmployeeDoesNotExistException(String message)
	{
		super(message);
	}

}
