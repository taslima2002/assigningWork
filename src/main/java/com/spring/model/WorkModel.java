package com.spring.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkModel {
	private int workId;
	private String workType;
	private LocalDate workstartDate;
	private LocalDate workEndDate;
	private String DomainRequirement;
	private int clientId;
	private int empId;
}
