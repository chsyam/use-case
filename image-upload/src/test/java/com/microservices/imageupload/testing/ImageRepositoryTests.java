package com.microservices.imageupload.testing;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.microservices.imageupload.bean.Image;
import com.microservices.imageupload.repository.ImageRepository;

@DataMongoTest
public class ImageRepositoryTests {

	@Autowired
	private ImageRepository imageRepository;

	@Test
	@DisplayName("JUnit test for save Image operation")
	public void givenUserObject_whenSave_thenReturnSavedUser() {

		Image image = new Image(12, 1, "0".getBytes());
		Image savedImage = imageRepository.save(image);

		assertThat(savedImage).isNotNull();
		assertThat(savedImage.getId()).isGreaterThan(0);
	}

	@Test
	@DisplayName("jUnit test for getting all Image objects")
	public void givenUserList_whenFindAll_thenUserList() {
		Image image1 = new Image(145, 1, "0".getBytes());
		Image image2 = new Image(265, 2, "0".getBytes());

		imageRepository.save(image1);
		imageRepository.save(image2);

		List<Image> imagesList = imageRepository.findAll();

		assertThat(imagesList).isNotNull();
		assertThat(imagesList.size()).isGreaterThanOrEqualTo(2);
	}

	@Test
	@DisplayName("JUnit test for getting Image by id operation")
	public void givenUserObject_whenFindById_thenReturnUserObject() {

		Image image = new Image(234, 1, "0".getBytes());
		imageRepository.save(image);

		Image savedImage = imageRepository.findById(image.getId()).get();

		assertThat(savedImage).isNotNull();
	}

	@Test
	@DisplayName("JUnit test for checking updated Image details operation")
	public void givenUserObject_whenUpdateEmployee_thenReturnUserObject() {
		Image image = new Image(123, 1, "0".getBytes());

		imageRepository.save(image);
		Image savedImage = imageRepository.findById(image.getId()).get();

		savedImage.setUserid(10);
		savedImage.setImage("0".getBytes());

		Image updatedImage = imageRepository.save(savedImage);

		assertThat(updatedImage.getUserid()).isEqualTo(10);
		assertThat(updatedImage.getImage()).isEqualTo("0".getBytes());
	}

	@Test
	@DisplayName("JUnit test for delete Image operation")
	public void givenUserObject_whenDelete_thenRemoveUser() {
		Image image = new Image(100, 1, "0".getBytes());

		imageRepository.save(image);
		imageRepository.deleteById(image.getId());

		Optional<Image> imageOptional = imageRepository.findById(image.getId());

		assertThat(imageOptional).isEmpty();
	}
}
