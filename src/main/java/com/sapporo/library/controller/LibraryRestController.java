package com.sapporo.library.controller;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapporo.library.entity.BookRegistData;
import com.sapporo.library.entity.LocationData;
import com.sapporo.library.entity.Section;
import com.sapporo.library.service.BookDataService;
import com.sapporo.library.service.CategoryService;
import com.sapporo.library.service.SectionService;
@CrossOrigin
@RestController
@RequestMapping("library")
public class LibraryRestController {
	
	@Autowired
	SectionService sectionService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	BookDataService bookDataService;
	
	@CrossOrigin
	@GetMapping("bookdata/{sectionId}")
	Response getBooks(@PathVariable("sectionId") Section sectionId) { 
		//指定された大区分の書籍一覧を取
		Response bookList = bookDataService.findBySectionId(sectionId);
		return bookList;
	}
	
	@GetMapping("bookdata")
	Response getBooks() {
		//全書籍データを取得
		 Response response = bookDataService.findAll();
		return response;
	}
	
	@CrossOrigin
	@PostMapping("bookdata/locationEdit")
//	@Produces("MediaType.APPLICATION_JSON")
	 Response getLocationEdit(@Validated @RequestBody LocationData locationData) {
		//所在変更処理
		 Response result = bookDataService.editLocation(locationData);
		 return result;
	 }
	
	@CrossOrigin
	@PostMapping("bookdata/bookRegist")
	Response registBookData(@Validated @RequestBody BookRegistData bookRegistData) {
		//書籍新規登録処理
		Response result = bookDataService.registBookData(bookRegistData);
		return result;
	}
	
	@CrossOrigin
	@PostMapping("bookdata/bookEdit")
	Response editBookData(@Validated @RequestBody BookRegistData bookRegistData) {
		//書籍データ変更処理
		Response result = bookDataService.editBookData(bookRegistData);
		return result;
	}

	@CrossOrigin
	@GetMapping("bookdata/bookId/{id}")
	Response getBookId(@PathVariable("id") String id) {
		//idで指定されたされた書籍を取得
		Response bookList = bookDataService.findById(Integer.parseInt(id));
	    return bookList;
	}
	
	//書籍大区分取得
	@CrossOrigin
	@GetMapping("section")
	Response getSectionAll() {
		//大区分リストを取得
		Response section = sectionService.findAll();
		return section;
	}
	
	@CrossOrigin
	@GetMapping("category/{sectionId}")
	Response getCategoryList(@PathVariable("sectionId") Section sectionId) {
//		int sectionNum = Integer.parseInt(section.getId());
		//指定された大区分に該当する小区分を取得
		Response category = categoryService.findBySectionId(sectionId);
		return category;
	}
	
	@CrossOrigin
	@GetMapping("category")
	Response getMenuList() {
		//指定された小区分の書籍リストを取得
		Response category = categoryService.findAll();
		return category;
	}
}
