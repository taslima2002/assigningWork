package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import com.spring.entity.Client;



@Repository
public interface ClientRepository extends JpaRepository<Client,Integer>{

	Client findByClientId(int clientId);
	
	boolean existsByClientId(int clientId);
	
	
}
