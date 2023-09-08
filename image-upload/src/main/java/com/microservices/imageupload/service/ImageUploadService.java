package com.microservices.imageupload.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.microservices.imageupload.bean.Image;
import com.microservices.imageupload.repository.ImageRepository;

@Service
public class ImageUploadService {

	private static Logger logger = LoggerFactory.getLogger(ImageUploadService.class);

	private ImageRepository imageRepository;

	public ImageUploadService(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}

	public int getUserId(String username, String password) {
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("name", username);
		uriVariables.put("password", password);
		logger.info("User service called");
		ResponseEntity<Image> responseEntity = new RestTemplate()
				.getForEntity("http://localhost:8080/users/{name}/{password}", Image.class, uriVariables);

		if (responseEntity == null) {
			throw new RuntimeException("credentials didn't match");
		}
		return responseEntity.getBody().getId();
	}

	public List<Image> retrieveAllImages() {
		return imageRepository.findAll();
	}

	public String saveImage(MultipartFile image, int id, String username, String password) throws IOException {
		logger.info("save-image-api is called");
		Image savedImage = new Image();
		savedImage.setId(id);
		savedImage.setImage(image.getBytes());
		savedImage.setUserid(getUserId(username, password));
		this.imageRepository.save(savedImage);
		logger.info("Image Uploaded successfully");
		return "Image Uploaded successfully";
	}

	public String deleteImage(int id, String name, String password) {
		int user_id = getUserId(name, password);

		Optional<Image> savedImage = this.imageRepository.findById(id);

		if (savedImage == null) {
			return String.format("Image with id -> %d is not found in database", id);
		} else if (user_id == savedImage.get().getId()) {
			this.imageRepository.deleteById(id);
			return String.format("Image with id -> %d is deleted successfully from the database", id);
		} else {
			return String.format(
					"You are not a valid user to delete this image with id -> %d.\n Please try again after sometime with valid credentials",
					id);
		}
	}

	public List<Image> imageUploadedByUser(String name, String password) {
		int user_id = getUserId(name, password);
		List<Image> ans = new ArrayList<Image>();
		List<Image> images = this.imageRepository.findAll();
		for (Image image : images) {
			if (image.getUserid() == user_id) {
				ans.add(image);
			}
		}
		return ans;
	}

	public String imagesCount() {
		return String.format("Total Number of Images uploaded =  %d", this.imageRepository.count());
	}

}
