package com.example.service.freeboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.model.FreeBoard;
import com.example.model.FreeBoardCategory;
import com.example.model.FreeBoardSearchVo;
import com.example.pageMaker.PageMaker;
import com.example.repository.FreeBoardRepository;

@Service
public class FreeBoardListService {

	@Autowired
	private FreeBoardRepository freeBoardRepository;
	
	@Autowired
	private PageMakerService pageMakerService;
	
	public FreeBoardSearchVo freeBoardList(int pageNum) {
		
		PageMaker pageMaker = pageMakerService.generatePageMaker(pageNum, 10, freeBoardRepository.findAll());
		
		PageRequest pageRequest = PageRequest.of(pageNum -1, 10, Sort.Direction.DESC, "freeId");
		
		Page<FreeBoard> freeboardPage = freeBoardRepository.findAll(pageRequest);
		
		List<FreeBoard> freeboardList = freeboardPage.getContent();
		
//		List<FreeBoardCategory> categoryList = freeBoardRepository.findByCategoryAndCount();
		
		FreeBoardSearchVo freeBoardSearchVo = new FreeBoardSearchVo();
		
		freeBoardSearchVo.setFreeBoardList(freeboardList);
		freeBoardSearchVo.setPageMaker(pageMaker);
//		freeBoardSearchVo.setCategory(categoryList);
				
		return freeBoardSearchVo;
	}
}
