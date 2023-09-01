package com.spring.controller;

import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;

import com.spring.exception.EmployeeNotFoundException;

import com.spring.model.EmployeeModel;

import com.spring.service.EmployeeService;



@RestController
@CrossOrigin("http://localhost:3000")
public class EmployeeController {
public static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeService employeeService;

	@GetMapping(value = "/getallemployee")
	public List<EmployeeModel> getAllEmployee() {
		logger.info("get all employees method started");
		return employeeService.getAllEmployee();
	}

	@GetMapping(value = "/searchemployees/{empExpertise}")
	public List<EmployeeModel> searchEmployeeByEmpExpertise(@PathVariable("empExpertise") String empExpertise)
			throws EmployeeNotFoundException {
		logger.info("search employee by expertise started");
		return employeeService.searchEmployees(empExpertise);

	}

	@GetMapping(value = "/findavailableemployee/{empAvailability}")
	public List<EmployeeModel> availableEmployees(@PathVariable("empAvailability") boolean empAvailability)
			throws EmployeeNotFoundException {
		logger.info("all the available employees method started");
		return employeeService.availableEmployees(empAvailability);
	}
}
