package com.microservices.userregistration.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.userregistration.bean.User;
import com.microservices.userregistration.kafka.KafkaProducer;
import com.microservices.userregistration.service.UserService;

@RestController
public class UserRegistrationController {

	private static Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);

	@Autowired
	private KafkaProducer kafkaProducer;
	@Autowired
	private UserService userService;

	public UserRegistrationController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public ResponseEntity<?> retrieveAllUsers() {
		List<User> users = this.userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@GetMapping("/users/{name}/{password}")
	public ResponseEntity<?> retrieveSpecificUser(@PathVariable String name, @PathVariable String password) {
		return ResponseEntity.ok(this.userService.specificUser(name, password));
	}

	@PostMapping("/users")
	public void saveUser(@RequestParam("name") String name, @RequestParam("password") String password) {

		User user = new User();
		user.setName(name);
		user.setPassword(password);

		logger.info("Message sent to kafka producer" + user.toString());
		kafkaProducer.sendMessage(user);
	}

	@DeleteMapping("users/delete/{id}/{password}")
	public void deleteUser(@PathVariable int id, @PathVariable String password) {
		this.userService.delete(id, password);
	}

	@DeleteMapping("users/delete/all")
	public void deleteAllUsers() {
		this.userService.deleteAll();
	}
}