package com.microservices.userregistration.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.microservices.userregistration.bean.User;
import com.microservices.userregistration.userRepository.UserRepository;

@Service
public class UserService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	public User specificUser(String name, String password) {
		User savedUser = userRepository.findByJPQL(name, password);
		if (savedUser != null)
			return savedUser;
		else if (userRepository.count() == 0) {
			logger.info(
					"User with those Credentials not found because the database is empty.....Throwing a RunTimeException");
			throw new RuntimeException("Data Base is Empty");
		} else {
			logger.info("Credentials are Not Correct.....Throwing a RunTimeException");
			throw new RuntimeException("Credentials Not Correct!!");
		}
	}

	@KafkaListener(topics = "user", groupId = "myGroup")
	public void consume(User user) {
		this.userRepository.save(user);
		logger.info(String.format("User with username -> %s successfully Registered", user.getName()));
	}

	public void delete(long id, String password) {
		Optional<User> user = this.userRepository.findById(id);
		if (user.get().getPassword().equals(password)) {
			this.userRepository.deleteById(id);
		}
	}

	public void deleteAll() {
		this.userRepository.deleteAll();
	}
}
