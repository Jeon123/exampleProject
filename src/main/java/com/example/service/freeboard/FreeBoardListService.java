package com.example.service.freeboard;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.model.FreeBoard;
import com.example.pageMaker.PageMaker;
import com.example.repository.FreeBoardRepository;

@Service
public class FreeBoardListService {

	@Autowired
	private FreeBoardRepository freeBoardRepository;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private PageMakerService pageMakerService;
	
	public String freeBoardList(int pageNum) {
		
		PageMaker pageMaker = pageMakerService.generatePageMaker(pageNum, 10, freeBoardRepository);
		
		PageRequest pageRequest = PageRequest.of(pageNum -1, 10, Sort.Direction.DESC, "freeId");
		Page<FreeBoard> freeboardPage = freeBoardRepository.findAll(pageRequest);
		
		if(freeboardPage.getSize() == 0) {
			session.setAttribute("boardList", new ArrayList<FreeBoard>());
			session.setAttribute("pageMaker", pageMaker);
			return "freeboard";
		}
				
		List<FreeBoard> freeboardList = freeboardPage.getContent();
		
		session.setAttribute("boardList", freeboardList);
		session.setAttribute("pageMaker", pageMaker);

		return "freeboard";
	}
	
}
