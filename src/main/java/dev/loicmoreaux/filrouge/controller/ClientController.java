package dev.loicmoreaux.filrouge.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.loicmoreaux.filrouge.entity.Client;
import dev.loicmoreaux.filrouge.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	@Autowired
	ClientService clientService;
	
	@GetMapping("/")
	public ResponseEntity<List<Client>> getAllClients(){
		return ResponseEntity.ok(clientService.getAllClients());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getClientById(@PathVariable("id") Long id){
		Optional<Client> optionalClient = clientService.getClientById(id);
		
		if(optionalClient.isPresent()) {
			return ResponseEntity.ok(optionalClient.get());
		}
		return new ResponseEntity<>(Map.of("error", "No client found with the specified id"), HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/")
	public ResponseEntity<Client> createClient(@RequestBody Client client){
		return new ResponseEntity<>(clientService.saveClient(client), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody Client client){
		return ResponseEntity.ok(client);
	}
}
