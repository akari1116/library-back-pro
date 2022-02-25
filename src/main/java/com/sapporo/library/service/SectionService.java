package com.sapporo.library.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapporo.library.Dto.BookStateResDto;
import com.sapporo.library.constans.EnumConstans.BookGetMessage;
import com.sapporo.library.entity.Section;
import com.sapporo.library.repository.SectionRepository;



@Service
public class SectionService {
	
	//書籍の大区分を取得するIF
	
	@Autowired
	SectionRepository sectionRepository;
	
	Logger logger = Logger.getLogger(BookDataService.class.getName());

	
	//全大区分を取得
	public Response findAll() {
		try {
			List<Section> sectionData = sectionRepository.findAll();
			//成功であれば大区分リストとステータスコードを201返却
			return Response.ok().entity(sectionData).build();
		} catch(Exception e) {
			e.printStackTrace();
			String res = this.generaetJson(BookGetMessage.SERVER_ERROR);
			//失敗であればステータスコード500をリターン
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	private String generaetJson(BookGetMessage enumObj) {

		ObjectMapper mapper = new ObjectMapper();
		BookStateResDto res = new BookStateResDto();

		logger.info(enumObj.getLog());
		
		//ステータスコードとメッセージをセット
		res.setStatus(enumObj.getStatus());
		res.setMessage(enumObj.getMessage());

		String json;

		try {
			json = mapper.writeValueAsString(res);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}

		return json;
	}
}
