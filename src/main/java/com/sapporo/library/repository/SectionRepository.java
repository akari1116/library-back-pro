package com.sapporo.library.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.sapporo.library.entity.Section;



public interface SectionRepository extends JpaRepository<Section, Integer>{
	
}
