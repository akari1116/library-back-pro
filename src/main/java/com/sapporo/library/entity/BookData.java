package com.sapporo.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "book_data")
public class BookData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String bookTitle;
	
	@ManyToOne
	@JoinColumn(name="sectionId")
	private Section sectionId;

	@ManyToOne
	@JoinColumn(name="categoryId")
	private Category categoryId;
	
	@Column
	private String location;
	
	@Column 
	private long isbnCode;
	
	@Column 
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

	public Section getSectionId() {
		return this.sectionId;
	}
	
	public void setSectionId(Section sectionId) {
		this.sectionId = sectionId;
	}
	
	public String getCategoryId() {
		return this.categoryId.getCategory();
	}

	public void setCategoryId(Category categoryId) {
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BookData [id=");
		builder.append(id);
		builder.append(", bookTitle=");
		builder.append(bookTitle);
		builder.append(", sectionId=");
		builder.append(sectionId);
		builder.append(", categoryId=");
		builder.append(categoryId);
		builder.append(", location=");
		builder.append(location);
		builder.append(", isbnCode=");
		builder.append(isbnCode);
		builder.append(", image=");
		builder.append(image);
		builder.append("]");
		return builder.toString();
	}

}
