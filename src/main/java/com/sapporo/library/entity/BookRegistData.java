package com.sapporo.library.entity;

import java.io.Serializable;

public class BookRegistData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String bookTitle;
	
	private int sectionId;	

	private int categoryId;
	
	private String location;
 
	private long isbnCode;
	
	private String image;
	
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public int getSectionId() {
		return this.sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	
	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public long getIsbnCode() {
		return this.isbnCode;
	}

	public void setIsbnCode(long isbnCode) {
		this.isbnCode = isbnCode;
	}
	
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
