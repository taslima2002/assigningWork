package com.spring.service;

import java.util.ArrayList;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.spring.entity.Employee;
import com.spring.entity.Work;

import com.spring.exception.EmployeeNotFoundException;
import com.spring.exception.WorkException;
import com.spring.exception.WorkNotFoundException;
import com.spring.model.EmployeeModel;
import com.spring.repository.EmployeeRepository;
;

@Service
public class EmployeeService {
	public static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	@Autowired
	EmployeeRepository employeeRepo;

	public Employee findById(int id) throws EmployeeNotFoundException {
		Employee e = employeeRepo.findById(id).orElse(null);

		if (e == null) {
			throw new EmployeeNotFoundException("NO employee present");
		}
		return e;
	}
	public List<EmployeeModel> getAllEmployee() {
		logger.info("get all employees from the employee list");
		List<Employee> empList = employeeRepo.findAll();
		List<EmployeeModel> employeeList = new ArrayList<>();
		for (Employee e : empList) {
			EmployeeModel empModel = new EmployeeModel();
			empModel.setEmpId(e.getEmpId());
			empModel.setEmpAvailability(e.isEmpAvailability());
			empModel.setEmpDept(e.getEmpDept());
			empModel.setEmpExperience(e.getEmpExperience());
			empModel.setEmpName(e.getEmpName());
			empModel.setEmpExpertise(e.getEmpExpertise());
			empModel.setRatings(e.getRatings());
			employeeList.add(empModel);
		}
		return employeeList;
	}

	public List<EmployeeModel> availableEmployees(boolean empAvailability) throws EmployeeNotFoundException {
		logger.info(" All the available employees");
		List<Employee> empList = employeeRepo.findByAvailableEmployee(empAvailability);
		if (empList.size() == 0) {
			throw new EmployeeNotFoundException("No employee");
		}
		List<EmployeeModel> employeeList = new ArrayList<>();
		for (Employee e : empList) {
			EmployeeModel empModel = new EmployeeModel();
			empModel.setEmpId(e.getEmpId());
			empModel.setEmpDept(e.getEmpDept());
			empModel.setEmpExperience(e.getEmpExperience());
			empModel.setEmpName(e.getEmpName());
			empModel.setEmpExpertise(e.getEmpExpertise());
			empModel.setRatings(e.getRatings());
			employeeList.add(empModel);
		}
		return employeeList;

	}

	public List<EmployeeModel> searchEmployees(String empExpertise) throws EmployeeNotFoundException {
	    List<Employee> list = employeeRepo.findByEmpExpertiseIgnoreCaseOrderByRatingDesc(empExpertise);
	    logger.info("search employee method");
	    if (list.isEmpty()) {
	        throw new EmployeeNotFoundException("No employee");
	    }
	    List<EmployeeModel> employeeList = new ArrayList<>();
	    for (Employee e : list) {
	        EmployeeModel empModel = new EmployeeModel();
	        empModel.setEmpId(e.getEmpId());
	        empModel.setEmpDept(e.getEmpDept());
	        empModel.setEmpExperience(e.getEmpExperience());
	        empModel.setEmpName(e.getEmpName());
	        empModel.setEmpExpertise(e.getEmpExpertise());
	        empModel.setRatings(e.getRatings());
	        employeeList.add(empModel);
	    }
	    return employeeList;
	}

	public String giveRatingsToEmployee(Work w, double rating) throws WorkNotFoundException, WorkException {
		logger.info("give ratings to employee method");
		Employee e = null;
		if (rating < 1 || rating > 5) {
			throw new WorkException("wrong rating,rate in between 1 to 5.");
		} else {
			e = w.getEmployee();
			e.setEmpAvailability(true);
			double rate = Math.round(((e.getRatings() * e.getEmpExperience()) + rating) / (e.getEmpExperience() + 1));
			e.setRatings(rate);
			e.setEmpExperience(e.getEmpExperience() + 1);
			employeeRepo.save(e);

			return "you have rated " + e.getEmpName() + " " + rating;
		}
	}
	public void updateEmployeeStatus(Employee e) {
		e.setEmpAvailability(false);
		employeeRepo.save(e);
	}

}