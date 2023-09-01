package com.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.exception.ClientNotFoundException;
import com.spring.model.ClientModel;
import com.spring.service.ClientService;

@RestController
@CrossOrigin("http://localhost:3000")
public class ClientController {
	
		public static final Logger logger = LoggerFactory.getLogger(WorkController.class);
		@Autowired
		ClientService clientService;
		
		@GetMapping(value ="/login/{clientId}/{password}")
		public ClientModel login(@PathVariable int clientId, @PathVariable String password)throws ClientNotFoundException {
			logger.info("Login method started");
			return clientService.login(clientId, password);
		}
		@PostMapping(value="/signup")
		public boolean signUp(@RequestBody ClientModel client) {
			logger.info("Sign Up method started");
			return clientService.signUp(client);
		}
}
