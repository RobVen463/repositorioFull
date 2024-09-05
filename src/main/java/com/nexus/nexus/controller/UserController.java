package com.nexus.nexus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.nexus.exceptions.EmailNotFoundException;
import com.nexus.nexus.model.User;
import com.nexus.nexus.service.UserService;

@RestController
@RequestMapping("/api/v1")
//CORS
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class UserController {
	
	//Mandamos a llamar Service
	private UserService userService;
	
	//Inyección de dependencias en el constructor
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//Mapear métodos (get, post, put, delete)
	@GetMapping("/users")
	public List<User> getMappingAll(){
		return userService.getAll();
	}
	
	//Mappear método Post que reciba un nuevo objeto y el body del mismo (@RequestBody) en PostMan tengo que construir el body de la entidad en formato json
	@PostMapping
	public User newUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	//Mappear método Delete que apunte a un id específico. Para ello, debemos permitir que el id sea variable en el endpoint (@PathVariable)
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable(name = "id") Long id) {
		userService.deleteUser(id);
	}
	
	//Mapear métodos get by id que apunte a un id específico
		@GetMapping("/nexusServices/{id}")
		public User getById(@PathVariable(name = "id") Long id) {
			return userService.getById(id);
		}
	
	//Mapear Método get by email aplicando la query (JPQL) y la exception
		// --- ResponseEntity Es una clase de Spring que me permite representar respuestas HTTP personalizables
		// --- @RequestParam(parametro) permite recibir parámetros y valores
		//Creamos EmailNotFoundException y su clase controller EmailNotFoundController
		@GetMapping("/usercorreo/email")
		public ResponseEntity<User> getByEmail(@RequestParam(name= "nexusmail") String email) {
			User userByEmail = userService.getByEmail(email);
			if (userByEmail == null) {
				throw new EmailNotFoundException(email);
			}
			return new ResponseEntity<User>(userByEmail, HttpStatus.OK) ;
		}
		
		//Mapear método update utilizando Put. Actualiza el password de un usuario. Necesitamos acceder al User mediante id (findId) y definir el nuevo valor
				@PutMapping("/nexusServices/{id}")
				public User updateUser(@RequestBody User user, @PathVariable(name = "id") Long id) {
					return userService.updateUser(user, id);
				}
		
		
		
		
		
		
		
	
	
}
