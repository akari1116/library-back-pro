package com.sapporo.library.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapporo.library.Dto.BookStateResDto;
import com.sapporo.library.constans.EnumConstans.BookGetMessage;
import com.sapporo.library.constans.EnumConstans.BookRegistMessage;
import com.sapporo.library.entity.BookData;
import com.sapporo.library.entity.BookRegistData;
import com.sapporo.library.entity.Category;
import com.sapporo.library.entity.LocationData;
import com.sapporo.library.entity.Section;
import com.sapporo.library.logic.BookLogic;
import com.sapporo.library.repository.BookDataRepository;



@Service
public class BookDataService {
	
	//書籍一覧を取得するIF
	
	@Autowired
	BookDataRepository bookDataRepository;
	@Inject
	BookLogic bookLogic;
	Logger logger = Logger.getLogger(BookDataService.class.getName());

	
	//全書籍データ一覧を取得
	public Response findAll() {
//		ObjectMapper mapper = new ObjectMapper();
		try {
			List<BookData> bookData = bookDataRepository.findAll();
//			List<BookData> list = bookLogic.bookDataListAsc(bookData);
//			String json = mapper.writeValueAsString(bookData);
			return Response.ok().entity(bookData).build();
		} catch(Exception e) {
			e.printStackTrace();
			BookStateResDto res = this.generaetJson(BookGetMessage.SERVER_ERROR);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	//指定された大区分の書籍データ一覧を取得
	public Response findBySectionId(Section id) {
		try {
			List<BookData> bookData = bookDataRepository.findBySectionId(id);
//			List<BookData> list = bookLogic.bookDataListAsc(bookData);
			return Response.ok().entity(bookData).build();
		} catch(Exception e) {
			e.printStackTrace();
			BookStateResDto res = this.generaetJson(BookGetMessage.SERVER_ERROR);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
		
	}
	
	public Response findById(int id) {
		//指定されたidの書籍を取得
		try {
			Optional<BookData> bookData  = bookDataRepository.findById(id);
			if(bookData.isPresent()) {
				return Response.ok().entity(bookData).build();
			} else {
				BookStateResDto res = this.generaetJson(BookGetMessage.NOTEXSITS);
				return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
			}
		} catch(Exception e) {
			e.printStackTrace();
			BookStateResDto res = this.generaetJson(BookGetMessage.SERVER_ERROR);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}

	public Response editLocation(LocationData locationData){
		//所在の変更
		int bookId = locationData.getId();
		String location = locationData.getLocation();
		try {
			if(bookId == 0 || location.equals("")) {
				BookStateResDto res = this.generaetJson(BookRegistMessage.EMPTY);
				return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
			}
			
			int result = bookDataRepository.editLocation(location, bookId);
			
			if(result != 0) {
				BookStateResDto res = this.generaetJson(BookRegistMessage.SUCCESS);
				return Response.status(Response.Status.CREATED).entity(res).build();
			} else {
				BookStateResDto res = this.generaetJson(BookRegistMessage.NOTEXSITS);
				return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			BookStateResDto res = this.generaetJson(BookRegistMessage.SERVER_ERROR);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	public Response registBookData(BookRegistData bookRegistData) {
		//書籍新規登録
		
		BookData bookData = new BookData();
		Section section = new Section();
		Category category = new Category();
		
		section.setId(bookRegistData.getSectionId());
		category.setId(bookRegistData.getCategoryId());
		
		bookData.setBookTitle(bookRegistData.getBookTitle());
		bookData.setSectionId(section);
		bookData.setCategoryId(category);
		bookData.setLocation(bookRegistData.getLocation());
		bookData.setIsbnCode(bookRegistData.getIsbnCode());
		bookData.setImage(bookRegistData.getImage());
				
		try {
		
			if(bookData.getBookTitle().equals("") || section.getId() == 0 || category.getId() == 0 || bookData.getLocation().equals("")
					|| bookData.getIsbnCode() == 0 || bookData.getImage().equals("")) {
				BookStateResDto res = this.generaetJson(BookRegistMessage.EMPTY);
				return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
			}
			
			bookDataRepository.saveAndFlush(bookData);
			BookStateResDto res = this.generaetJson(BookRegistMessage.SUCCESS);
			return Response.status(Response.Status.CREATED).entity(res).build();
			
		} catch(Exception e) {
			e.printStackTrace();
			BookStateResDto res = this.generaetJson(BookRegistMessage.SERVER_ERROR);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	public Response editBookData(BookRegistData bookRegistData) {
		//書籍変更
		
		int checkBookId = bookRegistData.getId();
		String checkBookTitle = bookRegistData.getBookTitle();
		int checkBookSectionId = bookRegistData.getSectionId();
		int checkBookCategoryId = bookRegistData.getCategoryId();
		String checkBookLocation = bookRegistData.getLocation();
		long checkBookIsbnCode = bookRegistData.getIsbnCode();
		String checkBookImage = bookRegistData.getImage();
		
		
		try {
			
			if(checkBookId == 0 || checkBookTitle.equals("") || checkBookSectionId == 0 || checkBookCategoryId == 0 ||
					checkBookLocation.equals("") || checkBookIsbnCode == 0 || checkBookImage.equals("")) {
				BookStateResDto res = this.generaetJson(BookRegistMessage.EMPTY);
				return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
			}
			
			int result = bookDataRepository.editBookData(checkBookId, checkBookTitle, checkBookSectionId,
					checkBookCategoryId, checkBookLocation, checkBookIsbnCode, checkBookImage);
			if(result != 0) {
				BookStateResDto res = this.generaetJson(BookRegistMessage.SUCCESS);
				return Response.status(Response.Status.CREATED).entity(res).build();
			} else {
				BookStateResDto res = this.generaetJson(BookRegistMessage.NOTEXSITS);
				return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BookStateResDto res = this.generaetJson(BookRegistMessage.SERVER_ERROR);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	private BookStateResDto generaetJson(BookRegistMessage enumObj) {

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
