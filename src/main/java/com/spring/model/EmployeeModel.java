package com.spring.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeModel {
	private int empId;
	private String empName;
	private double empExperience;
	private String empExpertise;
	private String empDept;
	private double ratings;

	private boolean empAvailability=true;


}
