package com.leavemanagement.exception;
/**
 * 
 * @author Sushil
 *
 */
public class InsufficientLeaveException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InsufficientLeaveException(String message)
	{
		super(message);
	}

}
