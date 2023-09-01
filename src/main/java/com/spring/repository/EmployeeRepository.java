package com.spring.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.spring.entity.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	@Query(value = "select e from Employee e where e.empExpertise=?1")
	public List<Employee> getEmployeeByExpertise(String expertise);

	@Query(value = "select e from Employee e where e.empExpertise=?1", nativeQuery = true)
	public List<Employee> getEmployeeByExpertiseByNativeQuery(String empExpertiseName);

	@Query(value = "SELECT * FROM Employee e WHERE e.emp_expertise = ?1 AND e.emp_availability = 1 ORDER BY e.ratings DESC", nativeQuery=true)
	public List<Employee> findByEmpExpertiseIgnoreCaseOrderByRatingDesc(String empExpertise);
	
	@Query(value = "SELECT * FROM Employee  WHERE  emp_availability = 1",nativeQuery = true)
	public List<Employee> findByAvailableEmployee(boolean empAvailability);


}
