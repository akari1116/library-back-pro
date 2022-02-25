package com.sapporo.library.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "category")
public class Category {
	
	@OneToMany(mappedBy = "categoryId", cascade=CascadeType.ALL)
	  private List<BookData> bookData;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="sectionId")
	private Section sectionId;
	
	@Column
	private String category;
	
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Section getSectionId() {
		return this.sectionId;
	}
	
	public void setSectionId(Section sectionId) {
		this.sectionId = sectionId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
