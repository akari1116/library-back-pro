package com.sapporo.library.logic;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sapporo.library.entity.BookData;

@Component
public class BookLogic {
	
	public List<BookData> bookDataListAsc(List<BookData> bookData) {
		
		Map<String, List<BookData>>  grpByComplexKeys = bookData.stream().collect(Collectors.groupingBy(BookData::getCategoryId));
		
		List<BookData> list = new ArrayList<>();
		for(Entry<String, List<BookData>> entry : grpByComplexKeys.entrySet()) {
			list.addAll(entry.getValue());
			
		}
		Collator collator = Collator.getInstance(Locale.JAPANESE);
		Collections.sort(list, collator);
		return list;
	}
	public class BookDataSort implements Comparator<BookData>{
		@Override
		public int compare(BookData obj1, BookData obj2) {
			return obj1.getBookTitle().compareTo(obj2.getBookTitle());
		}
	}
}
