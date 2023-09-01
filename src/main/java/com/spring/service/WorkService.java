package com.spring.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.Client;
import com.spring.entity.Employee;
import com.spring.entity.Work;
import com.spring.exception.ClientNotFoundException;
import com.spring.exception.EmployeeNotFoundException;
import com.spring.exception.WorkException;
import com.spring.exception.WorkNotFoundException;
import com.spring.model.WorkModel;

import com.spring.repository.WorkRepository;

@Service
public class WorkService {
	public static final Logger logger = LoggerFactory.getLogger(WorkService.class);
	@Autowired
	WorkRepository workRepo;

	@Autowired
	ClientService clientService;

	@Autowired
	EmployeeService employeeService;

	public String assignWork(WorkModel workModel) throws ClientNotFoundException, EmployeeNotFoundException {
		logger.info("assigning work");
		Client c = clientService.findClientById(workModel.getClientId());
		Employee e = employeeService.findById(workModel.getEmpId());
		if (!e.isEmpAvailability()) {
			return "This employee is already assigned to work, choose someone else";
		}
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plusDays(1);
		
		LocalDate workstartDate = workModel.getWorkstartDate();
	    LocalDate workEndDate = workModel.getWorkEndDate();
		if (workModel.getWorkstartDate().isBefore(today) || workModel.getWorkEndDate().isBefore(tomorrow)) {
			return "Invalid dates. The start date should be today or later, and the end date should be tomorrow or later.";
		}
		if (workstartDate.isAfter(workEndDate)) {
	        return "Invalid dates. The start date should not be after the end date.";
	    } 
		employeeService.updateEmployeeStatus(e);
		e.setEmpAvailability(false);
		Work work = new Work();
		work.setClient(c);
		work.setEmployee(e);
		work.setDomainRequirement(workModel.getDomainRequirement());
		work.setWorkEndDate(workModel.getWorkEndDate());
		work.setWorkstartDate(workModel.getWorkstartDate());
		work.setWorkType(workModel.getWorkType());
		workRepo.save(work);
		return "Your work is assigned to " + e.getEmpName();
	}

	public Work findById(int id) throws WorkNotFoundException {
		Work w = workRepo.findById(id).orElse(null);
		if (w == null) {
			throw new WorkNotFoundException("NO such work");
		}
		return w;
	}

	public List<WorkModel> getWorksbyClientId(int clientId) throws ClientNotFoundException {
		Client c = clientService.findClientById(clientId);
		if (c == null) {
			throw new ClientNotFoundException();
		}
		List<WorkModel> workModelList = new ArrayList<>();
		List<Work> works= workRepo.findWorksByClientId(clientId);
		for(Work w:works) {
			WorkModel workModel= new WorkModel(w.getWorkId(), w.getWorkType(), w.getWorkstartDate(), w.getWorkEndDate(),w.getDomainRequirement(),w.getClient().getClientId(),w.getEmployee().getEmpId());
			workModelList.add(workModel);
			
		}
		return workModelList;
	}

	public String giveRatingsToEmployee(int workId, double rating) throws WorkNotFoundException, WorkException {
		logger.info("give rating to a employee");
		if (rating < 1 || rating > 5) {
			throw new WorkException("wrong rating,rate in between 1 to 5.");
		}
		Work w = findById(workId);
		if (w.getStatus().equalsIgnoreCase("ongoing")) {
			w.setStatus("Completed");
			workRepo.save(w);
			return employeeService.giveRatingsToEmployee(w, rating);
		}

		else
			throw new WorkException("This work has already been Completed and rated.");

	}

	public int getWorkIdByEmployeeId(int empId) throws WorkNotFoundException {
		Work works = workRepo.findByEmpId(empId);
		if (works != null) {
			return works.getWorkId();
		} else {
			throw new WorkNotFoundException("No work is found for this employee");
		}
	}

}
