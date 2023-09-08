package com.microservices.imageupload;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.microservices.imageupload.repository.ImageRepository;

@DataJpaTest
public class ImageUploadRepositoryTests {

	@Autowired
	private ImageRepository imageRepository;

	@Test
	@DisplayName("JUnit test for save employee operation")
	public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

//		Image savedEmployee = imageRepository.save(employee);

//		assertThat(savedEmployee).isNotNull();
//		assertThat(savedEmployee.getId()).isGreaterThan(0);
	}

}
