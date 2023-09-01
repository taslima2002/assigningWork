package com.spring.controller;

import java.util.List;


import org.slf4j.Logger;



import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.spring.exception.ClientNotFoundException;
import com.spring.exception.EmployeeNotFoundException;
import com.spring.exception.WorkException;
import com.spring.exception.WorkNotFoundException;
import com.spring.model.WorkModel;
import com.spring.service.WorkService;



@RestController
@CrossOrigin("http://localhost:3000")
public class WorkController {
	public static final Logger logger = LoggerFactory.getLogger(WorkController.class);
	@Autowired
	WorkService workService;
	
	@PostMapping(value="/assignwork")
	public String assignWork(@RequestBody WorkModel workModel)throws ClientNotFoundException,EmployeeNotFoundException{
		logger.info("assign work method started");
		return workService.assignWork(workModel);
	}
	
	@PutMapping(value="/giveratingstoemployee/{work}/{rating}")
	public String giveRatingsToEmployee(@PathVariable("work") int workId, @PathVariable("rating") double rating) throws WorkNotFoundException, WorkException{
		logger.info("give rating method started");
		return workService.giveRatingsToEmployee(workId, rating);
	}
	@GetMapping(value="/getworkidbyempid/{employeeId}")
	public int getWorkIdByEmployeeId(@PathVariable("employeeId") int employeeId) throws WorkNotFoundException{
		logger.info("Get workId By Employee Id method started");
		return workService.getWorkIdByEmployeeId(employeeId);
	}
	@GetMapping(value="/getworksbyclientid/{clientId}")
	public List<WorkModel> getWorksbyClientId(@PathVariable("clientId") int clientId) throws ClientNotFoundException {	
		logger.info("Get Works By Client Id method started");
	return workService.getWorksbyClientId(clientId);
	}
	
}
