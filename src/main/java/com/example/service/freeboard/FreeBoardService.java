package com.example.service.freeboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.model.FreeBoard;
import com.example.model.FreeBoardSearchVo;
import com.example.pageMaker.PageMaker;
import com.example.repository.FreeBoardRepository;

@Service
public class FreeBoardService {

	@Autowired
	private FreeBoardRepository freeBoardRepository;
	
	@Autowired
	private PageMakerService pageMakerService;
	
	//FreeBoard List
	public FreeBoardSearchVo freeBoardList(int pageNum) {
		
		PageMaker pageMaker = pageMakerService.generatePageMaker(pageNum, 10, freeBoardRepository.findAll());
		
		PageRequest pageRequest = PageRequest.of(pageNum -1, 10, Sort.Direction.DESC, "freeId");
		
		Page<FreeBoard> freeboardPage = freeBoardRepository.findAll(pageRequest);
		
		List<FreeBoard> freeboardList = freeboardPage.getContent();
		
		FreeBoardSearchVo freeBoardSearchVo = new FreeBoardSearchVo();
		
		freeBoardSearchVo.setFreeBoardList(freeboardList);
		freeBoardSearchVo.setPageMaker(pageMaker);

		return freeBoardSearchVo;
	}
	
	//FreeBoard Write
	public FreeBoard write(FreeBoard freeBoard) {
		
		return freeBoardRepository.save(freeBoard);
	}

	//FreeBoard Update
	public FreeBoard update(FreeBoard freeBoard) {
		
		return freeBoardRepository.save(freeBoard);
	}
	
	//FreeBoard Delete
	public void delete(Long freeId) {
		freeBoardRepository.deleteById(freeId);
	}
	
	//FreeBoard Info
	public FreeBoard getFreeBoardInfo(String stringFreeId) {
		Long freeId = Long.parseLong(stringFreeId);
		
		return freeBoardRepository.findByFreeId(freeId);
	}
	
	//FreeBoard Search Start
	public FreeBoardSearchVo freeBoardSearchList(String searchText, int pageNum) {
		
		PageMaker pageMaker = new PageMaker();
		
		List<FreeBoard> freeboardList = new ArrayList<FreeBoard>();
		
		pageMaker = pageMakerService.generatePageMaker(pageNum, 10, freeBoardRepository.findByTitle(searchText));
		PageRequest pageRequest = PageRequest.of(pageNum -1, 10, Sort.Direction.DESC, "free_id");
		freeboardList = freeBoardRepository.findByTitlePagination(searchText, pageRequest);
		
		FreeBoardSearchVo freeBoardSearchVo = new FreeBoardSearchVo();
		
		freeBoardSearchVo.setFreeBoardList(freeboardList);
		freeBoardSearchVo.setPageMaker(pageMaker);
		
		return freeBoardSearchVo;
	}
	
	public FreeBoardSearchVo freeBoardCategoryList(String categoryText, int pageNum) {
		
		PageMaker pageMaker = new PageMaker();
		
		List<FreeBoard> freeboardList = new ArrayList<FreeBoard>();
		
		pageMaker = pageMakerService.generatePageMaker(pageNum, 10, freeBoardRepository.findByCategory(categoryText));
		PageRequest pageRequest = PageRequest.of(pageNum -1, 10, Sort.Direction.DESC, "free_id");
		freeboardList = freeBoardRepository.findByCategoryPagination(categoryText, pageRequest);
		
		FreeBoardSearchVo freeBoardSearchVo = new FreeBoardSearchVo();
		
		freeBoardSearchVo.setFreeBoardList(freeboardList);
		freeBoardSearchVo.setPageMaker(pageMaker);
		
		return freeBoardSearchVo;
	}
	//FreeBoard Search End
	
	//FreeBoard Excel Download
	public <T> List<T> freeBoardAllList() {
		List<T> freeBoardList = (List<T>) freeBoardRepository.findAll(new Sort(Sort.Direction.DESC, "freeId"));
		
		return freeBoardList;
	}
}
