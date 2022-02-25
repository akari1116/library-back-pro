package com.sapporo.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapporo.library.entity.Category;
import com.sapporo.library.entity.Section;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	public List<Category> findAll();
	public List<Category> findBySectionId(Section sectionId);
}
