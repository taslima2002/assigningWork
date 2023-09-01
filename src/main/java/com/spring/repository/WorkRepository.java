package com.spring.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.spring.entity.Work;

@Repository
public interface WorkRepository extends JpaRepository<Work, Integer> {
	//List<Work> findByClient(Client client);

	@Query(value="Select w from Work w where w.client.clientId = ?1 ")
	List<Work> findWorksByClientId(int clientId);
	
	@Query(value="Select w from Work w where w.employee.empId=?1 and w.status='ongoing'")
	Work findByEmpId(int empId);
	
}
