package com.microservices.userregistration.testing;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.microservices.userregistration.bean.User;
import com.microservices.userregistration.userRepository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTesting {

	@Autowired
	private UserRepository userRepository;

	@Test
	@DisplayName("JUnit test for save user operation")
	public void givenUserObject_whenSave_thenReturnSavedUser() {

		User user = new User("syam kumar", "1234");
		User savedUser = this.userRepository.save(user);

		assertThat(savedUser).isNotNull();
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("jUnit test for getting all user objects")
	public void givenUserList_whenFindAll_thenUserList() {
		User user1 = new User("syam kumar", "1234");
		User user2 = new User("siva kumar", "1234");

		this.userRepository.save(user1);
		this.userRepository.save(user2);

		List<User> userList = this.userRepository.findAll();

		assertThat(userList).isNotNull();
		assertThat(userList.size()).isGreaterThanOrEqualTo(2);
	}

	@Test
	@DisplayName("JUnit test for getting User by id operation")
	public void givenUserObject_whenFindById_thenReturnUserObject() {
		User user = new User("syam kumar", "1234");

		this.userRepository.save(user);

		User savedUser = this.userRepository.findById(user.getId()).get();

		assertThat(savedUser).isNotNull();
	}

	@Test
	@DisplayName("JUnit test for getting User by username")
	public void givenUserName_whenFindByName_thenReturnUserObject() {
		User user = new User("syam kumar", "1234");

		this.userRepository.save(user);

		User savedUser = this.userRepository.findByName(user.getName());
		assertThat(savedUser).isNotNull();
	}

	@Test
	@DisplayName("JUnit test for checking updated user details operation")
	public void givenUserObject_whenUpdateUser_thenReturnUserObject() {
		User user = new User("syam kumar", "1234");

		this.userRepository.save(user);
		User savedUser = this.userRepository.findById(user.getId()).get();

		savedUser.setName("kummaasdh");
		savedUser.setPassword("4356");

		User updatedUser = this.userRepository.save(user);

		assertThat(updatedUser.getName()).isEqualTo("kummaasdh");
		assertThat(updatedUser.getPassword()).isEqualTo("4356");
	}

	@Test
	@DisplayName("JUnit test for delete user operation")
	public void givenUserObject_whenDelete_thenRemoveUser() {
		User user = new User("syam kumar", "1234");

		this.userRepository.save(user);
		this.userRepository.deleteById(user.getId());

		Optional<User> userOptional = this.userRepository.findById(user.getId());

		assertThat(userOptional).isEmpty();
	}

	@Test
	@DisplayName("JUnit test for getting User using cusrtome Query")
	public void givenUserObject_whenFindByNameAndPassword_thenReturnUser() {
		User user = new User("syam kumar", "1234");

		this.userRepository.save(user);
		User savedUser = this.userRepository.findByJPQL(user.getName(), user.getPassword());

		assertThat(savedUser).isNotNull();
	}
}
