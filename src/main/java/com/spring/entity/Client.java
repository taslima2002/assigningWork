package com.spring.entity;

import java.util.List;


import jakarta.persistence.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
	@Id
	private int clientId;
	private String password;
	private String clientName;
	private String companyName;
	private String location;

	
	@OneToMany(mappedBy="client",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Work> workList;
	
}
