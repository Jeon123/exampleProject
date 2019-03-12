package com.example.service.freeboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.model.FreeBoard;
import com.example.model.FreeBoardSearchVo;
import com.example.pageMaker.PageMaker;
import com.example.repository.FreeBoardRepository;

@Service
public class FreeBoardSearch {

	@Autowired
	private FreeBoardRepository freeBoardRepository;
	
	@Autowired
	private PageMakerService pageMakerService;
	
	public FreeBoardSearchVo freeBoardList(String category, String searchText, int pageNum) {
		
		PageMaker pageMaker = new PageMaker();
		
		List<FreeBoard> freeboardList = new ArrayList<FreeBoard>();
		
		if(category.equals("search")) {
			pageMaker = pageMakerService.generatePageMaker(pageNum, 10, freeBoardRepository.findByTitle(searchText));
			PageRequest pageRequest = PageRequest.of(pageNum -1, 10, Sort.Direction.DESC, "free_id");
			freeboardList = freeBoardRepository.findByTitle(searchText, pageRequest);
		} else {
			pageMaker = pageMakerService.generatePageMaker(pageNum, 10, freeBoardRepository.findByTitle(category, searchText));
			PageRequest pageRequest = PageRequest.of(pageNum -1, 10, Sort.Direction.DESC, "free_id");
			freeboardList = freeBoardRepository.findByTitle(category, searchText, pageRequest);
		}
		
		FreeBoardSearchVo freeBoardSearchVo = new FreeBoardSearchVo();
		
		freeBoardSearchVo.setFreeBoardList(freeboardList);
		freeBoardSearchVo.setPageMaker(pageMaker);
		
		return freeBoardSearchVo;
	}
}
