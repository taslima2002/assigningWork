package com.spring.entity;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="work")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Work {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int workId;
	private String workType;
	private LocalDate workstartDate;
	private LocalDate workEndDate;
	private String DomainRequirement;
	private String status="ongoing";
	
	@ManyToOne(fetch = FetchType.EAGER)
	
	@JoinColumn(name="employee_id")
	@JsonIgnoreProperties("workList")
	private Employee employee;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="clientId")
	@JsonIgnoreProperties()
	private Client client;

	


}
