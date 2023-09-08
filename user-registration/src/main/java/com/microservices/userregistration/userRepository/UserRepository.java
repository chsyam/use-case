package com.microservices.userregistration.userRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.microservices.userregistration.bean.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select e from User e where e.name= ?1 and e.password = ?2")
	User findByJPQL(String name, String password);

	@Query("select e from User e where e.name = ?1")
	User findByName(String name);
}
