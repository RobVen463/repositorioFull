package com.nexus.nexus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexus.nexus.exceptions.UserNotFoundException;
import com.nexus.nexus.model.User;
import com.nexus.nexus.repository.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;

	//Inyección de Dependencias en el constructor
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// Método para obtener todos los usuarios 
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	// Método para crear un nuevo usuario
		public User createUser(User newUser) {
			return userRepository.save(newUser);
		}
		
	//Método para eliminar un usuario mediante id
		public void deleteUser(Long id) {
			if (userRepository.existsById(id)) {
				userRepository.deleteById(id);
			}else {
				throw new UserNotFoundException(id);
			}
		}
		
		//Método para recuperar usuarios por id (validar si existe)
		public User getById(Long id) {
			return userRepository.findById(id)
					.orElseThrow(() -> new UserNotFoundException(id));
		}
		
		// Método para recuperar usuario por Id (sin excepción, de tipo Optional)
		/*
		public Optional<User> getById(Long id) {
			return userRepository.findById(id);
		}
		*/
		
		//Método para recuperar usuarios por email (Con excepciones de tipo User)
		public User getByEmail(String email) {
			return userRepository.findByemail(email);
		}		
		
		//Método para actualizar información de usuario, permitiendo modificar el password
		public User updateUser(User user, Long id) {
			return userRepository.findById(id)
					.map(userMap ->{
						userMap.setPassword(user.getPassword());
						return userRepository.save(userMap);
					})
					.orElseThrow(() -> new UserNotFoundException(id));
		}
		
		//Método para recuperar usuarios por username
		
		
		
		
	
}
