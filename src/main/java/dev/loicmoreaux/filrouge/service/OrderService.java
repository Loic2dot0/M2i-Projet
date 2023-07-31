package dev.loicmoreaux.filrouge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.loicmoreaux.filrouge.dao.OrderRepository;
import dev.loicmoreaux.filrouge.entity.Order;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	public List<Order> getAllOrders(){
		return orderRepository.findAll();
	}
	
	public Optional<Order> getOrderById(Long id){
		return orderRepository.findById(id);
	}
	
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}
	
	public Order updateOrder(Order order) {
		return orderRepository.save(order);
	}
	
	public boolean deleteOrder(Long id) {
		orderRepository.deleteById(id);
		return !orderRepository.existsById(id);
	}
	
}
