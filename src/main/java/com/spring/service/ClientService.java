package com.spring.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.Client;

import com.spring.exception.ClientNotFoundException;
import com.spring.model.ClientModel;
import com.spring.repository.ClientRepository;

@Service
public class ClientService {
	@Autowired
	ClientRepository clientRepo;

	
	public Client findClientById(int id)throws ClientNotFoundException {
		Client c = clientRepo.findById(id).orElse(null);
		if(c==null) {
			throw new ClientNotFoundException(id+" this");
		}
		return c;
	}
	public ClientModel login(int clientId, String password)throws ClientNotFoundException {
		Client c=clientRepo.findByClientId(clientId);
		if( !c.getPassword().equals(password)) {
			throw new ClientNotFoundException("password incorrect");
		}else {
		return new ClientModel(c.getClientId(), c.getPassword(),c.getClientName(),c.getCompanyName(),c.getLocation());
		}
	}
	
	public boolean signUp(ClientModel client) {
		if(clientRepo.existsByClientId(client.getClientId())) {
			return false;
		}else {
		Client clieent=new Client();
		clieent.setClientId(client.getClientId());
		clieent.setPassword(client.getPassword());
		clieent.setClientName(client.getClientName());
		clieent.setCompanyName(client.getCompanyName());
		clieent.setLocation(client.getLocation());
		clientRepo.save(clieent);
		
		return true;
	}
	}
    
   
}
