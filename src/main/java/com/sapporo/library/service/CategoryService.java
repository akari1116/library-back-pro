package com.sapporo.library.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapporo.library.Dto.BookStateResDto;
import com.sapporo.library.constans.EnumConstans.BookGetMessage;
import com.sapporo.library.entity.Category;
import com.sapporo.library.entity.Section;
import com.sapporo.library.repository.CategoryRepository;

@Service
public class CategoryService {
	
	//小区分を取得するIF
	
	@Autowired
	CategoryRepository categoryRepository;
	
	Logger logger = Logger.getLogger(BookDataService.class.getName());

	
	public Response findAll() {
		//全小区分リストを取得
		try {
			List<Category> categoryData = categoryRepository.findAll();
			return Response.ok().entity(categoryData).build();
		} catch(Exception e) {
			e.printStackTrace();
			BookStateResDto res = this.generaetJson(BookGetMessage.SERVER_ERROR);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	public Response findBySectionId(Section sectionId) {
		//指定された大区分の小区分を取得
		try {
			List<Category> categoryData =  categoryRepository.findBySectionId(sectionId);
			return Response.ok().entity(categoryData).build();
		} catch(Exception e) {
			e.printStackTrace();
			BookStateResDto res = this.generaetJson(BookGetMessage.SERVER_ERROR);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	private BookStateResDto generaetJson(BookGetMessage enumObj) {

//		ObjectMapper mapper = new ObjectMapper();
		BookStateResDto res = new BookStateResDto();

		logger.info(enumObj.getLog());
		res.setStatus(enumObj.getStatus());
		res.setMessage(enumObj.getMessage());

//		String json;
//
//		try {
//			json = mapper.writeValueAsString(res);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//			return null;
//		}

		return res;
	}
}
