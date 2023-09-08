package com.microservices.imageupload.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.microservices.imageupload.bean.Image;

public interface ImageRepository extends MongoRepository<Image, Integer> {

}
