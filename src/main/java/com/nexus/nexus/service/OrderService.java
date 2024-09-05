package com.nexus.nexus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexus.nexus.exceptions.EmailNotFoundException;
import com.nexus.nexus.model.Order;
import com.nexus.nexus.model.User;
import com.nexus.nexus.repository.OrderRepository;
import com.nexus.nexus.repository.UserRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class OrderService {
	
	private OrderRepository orderRepository;
	private UserRepository userRepository;

	@Autowired
	public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
	}
	
	// GetAll
		public List<Order> getAll() {
			return orderRepository.findAll();
		}
		
		// Post 
		public Order newOrder(Order order, String email) {
			User existingUser = userRepository.findByemail(email);
			if (existingUser == null) {
				throw new EmailNotFoundException(email);
			}
			
			// asociar la orden con el usuario existente
			order.setUser(existingUser);

			return orderRepository.save(order);
		}
	
	
	
	
}
