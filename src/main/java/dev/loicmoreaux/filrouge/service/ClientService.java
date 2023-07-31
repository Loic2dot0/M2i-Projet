package dev.loicmoreaux.filrouge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.loicmoreaux.filrouge.dao.ClientRepository;
import dev.loicmoreaux.filrouge.entity.Client;

@Service
public class ClientService {
	
	@Autowired
	ClientRepository clientRepository;
	
	public List<Client> getAllClients(){
		return clientRepository.findAll();
	}
	
	public Optional<Client> getClientById(Long id){
		return clientRepository.findById(id);
	}
	
	public Client saveClient(Client client) {
		return clientRepository.save(client);		
	}
	
	public Client updateClient(Client client) {
		return clientRepository.save(client);
	}
	
	public boolean deleteClient(Long id) {
		clientRepository.deleteById(id);
        return !clientRepository.existsById(id);
	}
		
}
