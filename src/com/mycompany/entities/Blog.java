package com.mycompany.entities;

import com.codename1.io.File;

public class Blog {
	
	private String title, content, image;
	private int id;
	
	public Blog(String title, String content, String image) {
		this.title = title;
		this.content = content;
		this.image = image;
	}
	
	

	public Blog(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}



	public Blog() {
	}



	@Override
	public String toString() {
		return "Blog [title=" + title + ", content=" + content + ", image=" + image + "]";
	}
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
