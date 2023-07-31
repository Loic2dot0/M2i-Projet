package dev.loicmoreaux.filrouge.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public ResponseEntity<?> updateClient(@PathVariable("id") Long id, @RequestBody Client client){
		if(id == null  || id != client.getId()) return ResponseEntity.badRequest().body(Map.of("error", "The id parameter must not be null and must match the client's id")); 
		
		if(clientService.getClientById(id).isPresent()) return ResponseEntity.ok(clientService.updateClient(client));
		
		return new ResponseEntity<>(Map.of("error", "No client found with the specified id"), HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteClient(@PathVariable("id") Long id){
		if(id == null) return ResponseEntity.badRequest().body(Map.of("error", "The id parameter must not be null"));
		
		if(clientService.getClientById(id).isEmpty()) return new ResponseEntity<>(Map.of("error", "No client found with the specified id"), HttpStatus.NOT_FOUND);
		
		if(clientService.deleteClient(id)) return ResponseEntity.ok(Map.of("message", "The client was successfully deleted"));
		
		return ResponseEntity.internalServerError().body(Map.of("error", "An error occurred while attempting to delete the client"));
			
	}
}
