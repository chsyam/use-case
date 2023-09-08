package com.microservices.imageupload.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.microservices.imageupload.bean.Image;
import com.microservices.imageupload.service.ImageUploadService;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class ImageUploadController {

	private Logger logger = LoggerFactory.getLogger(ImageUploadController.class);

	@Autowired
	private ImageUploadService imageUploadService;

	@GetMapping("/images")
	public ResponseEntity<?> getAllImages() {
		return ResponseEntity.ok(this.imageUploadService.retrieveAllImages());
	}

	@PostMapping("/images")
	@Retry(name = "image-upload-exception", fallbackMethod = "handleAnyExceptions")
	public void saveImage(@RequestParam("image") MultipartFile image, @RequestParam("id") Integer id,
			@RequestParam("username") String username, @RequestParam String password) throws IOException {
		this.imageUploadService.saveImage(image, id, username, password);
	}

	public void handleAnyExceptions(Exception ex) {
		logger.info("User service is not working.....Please try again after sometime");
	}

	@DeleteMapping("/images/{id}/{name}/{password}")
	public String deleteImage(@PathVariable int id, @PathVariable String name, @PathVariable String password) {
		return this.imageUploadService.deleteImage(id, name, password);
	}

	@GetMapping("/images/{name}/{password}")
	public ResponseEntity<List<Image>> imagesUploadedBySpecificUser(@PathVariable String name,
			@PathVariable String password) {
		return ResponseEntity.ok(this.imageUploadService.imageUploadedByUser(name, password));
	}

	@GetMapping("/images/countImages")
	public String imagesUploaded() {
		return this.imageUploadService.imagesCount();
	}
}
