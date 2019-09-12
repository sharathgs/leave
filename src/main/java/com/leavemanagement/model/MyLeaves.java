package com.leavemanagement.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table
public class MyLeaves {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int myLeavesId;
	private int employeeId;
	private String status;
	private String leaveType;
	private LocalDate appliedDate;
	private LocalDate forDate;
	private String remark;

}
