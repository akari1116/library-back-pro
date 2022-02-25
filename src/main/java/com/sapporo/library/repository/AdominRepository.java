package com.sapporo.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapporo.library.entity.Adomin;

public interface AdominRepository  extends JpaRepository<Adomin, Integer>{
	
//  @Query("SELECT d FROM BookData d WHERE d.user_id = :userId")
	public Adomin findByEmail(String userId);

}
