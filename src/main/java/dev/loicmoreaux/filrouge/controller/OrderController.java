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

import dev.loicmoreaux.filrouge.entity.Order;
import dev.loicmoreaux.filrouge.service.ClientService;
import dev.loicmoreaux.filrouge.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ClientService clientService;
	
	@GetMapping("/")
	public ResponseEntity<List<Order>> getAllOrders(){
		return ResponseEntity.ok(orderService.getAllOrders());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getOrderById(@PathVariable("id") Long id){
		Optional<Order> optionalOrder = orderService.getOrderById(id);
		
		if(optionalOrder.isPresent()) return ResponseEntity.ok(optionalOrder.get());
		
		return new ResponseEntity<>(Map.of("error", "No order found with the specified id"), HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/")
	public ResponseEntity<?> createOrder(@RequestBody Order order){
		if(order.getId() != null) return ResponseEntity.badRequest().body(Map.of("error", "The order ID must not be set as it will be automatically generated"));
		
		if(order.getTypePresta() == null) return ResponseEntity.badRequest().body(Map.of("error", "Arg typePresta must not be null"));
		if(order.getDesignation() == null) return ResponseEntity.badRequest().body(Map.of("error", "Arg designation must not be null"));
		if(order.getClient() == null || order.getClient().getId() == null) return ResponseEntity.badRequest().body(Map.of("error", "Arg client must not be null"));
		if(clientService.getClientById(order.getClient().getId()).isEmpty()) return ResponseEntity.badRequest().body(Map.of("error", "No client found with the specified id"));
		
		return new ResponseEntity<>(orderService.saveOrder(order), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateOrder(@PathVariable("id") Long id, @RequestBody Order order){
		if(id == null  || id != order.getId()) return ResponseEntity.badRequest().body(Map.of("error", "The id parameter must not be null and must match the order's id")); 
		
		if(order.getTypePresta() == null) return ResponseEntity.badRequest().body(Map.of("error", "Arg typePresta must not be null"));
		if(order.getDesignation() == null) return ResponseEntity.badRequest().body(Map.of("error", "Arg designation must not be null"));
		if(order.getClient() == null || order.getClient().getId() == null) return ResponseEntity.badRequest().body(Map.of("error", "Arg client must not be null"));
		if(clientService.getClientById(order.getClient().getId()).isEmpty()) return ResponseEntity.badRequest().body(Map.of("error", "No client found with the specified id"));
				
		if(orderService.getOrderById(id).isPresent()) return ResponseEntity.ok(orderService.updateOrder(order));
		
		return new ResponseEntity<>(Map.of("error", "No order found with the specified id"), HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id){
		if(id == null) return ResponseEntity.badRequest().body(Map.of("error", "The id parameter must not be null"));
		
		if(orderService.getOrderById(id).isEmpty()) return new ResponseEntity<>(Map.of("error", "No order found with the specified id"), HttpStatus.NOT_FOUND);
		
		if(orderService.deleteOrder(id)) return ResponseEntity.ok(Map.of("message", "The order was successfully deleted"));
		
		return ResponseEntity.internalServerError().body(Map.of("error", "An error occurred while attempting to delete the order"));
			
	}
}
