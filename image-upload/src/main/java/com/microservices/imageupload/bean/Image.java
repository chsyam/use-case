package com.microservices.imageupload.bean;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "images")
public class Image {
	private int id;
	private int userid;
	private byte[] image;

	public Image(int id, int userid, byte[] image) {
		super();
		this.id = id;
		this.userid = userid;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Image() {
		super();
	}

}
