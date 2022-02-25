package com.sapporo.library.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sapporo.library.entity.BookData;
import com.sapporo.library.entity.Section;



public interface BookDataRepository extends JpaRepository<BookData, Integer> {
	
//    @Query("SELECT d FROM BookData d WHERE d.sectionId = :sectionId")
	public List<BookData> findBySectionId(@Param("sectionId") Section sectionId);
	
	public Optional<BookData> findById(int bookId);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE book_data set location =  ?1 where id = ?2", nativeQuery=true)
	public int editLocation(@Param("location") String location, @Param("id") int id);
	
//	@Transactional
//	@Modifying
//	@Query(value="UPDATE book_data set book_title = :bookDataDto.getBookTitle(), section_id = :bookDataDto.getSectionId(), category_id = :bookDataDto.getCategoryId(), location = :bookDataDto.getLocation(), "
//			+ "isbn_code =  :bookDataDto.getIsbnCode(), image = :bookDataDto.getImage()", nativeQuery=true)
//	public int editBookData(@Param("bookDataDto") BookDataDto bookDataDto);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE book_data set book_title = ?2, section_id = ?3, category_id = ?4, location = ?5, "
			+ "isbn_code = ?6, image = ?7 where id = ?1", nativeQuery=true)
	public int editBookData(@Param("id") int id, @Param("title") String title, @Param("sectionId") int sectionId, @Param("categoryId") int categoryId,
			@Param("location") String location, @Param("isbnCode") long isbnCode, @Param("image") String image);
    
}
