package com.example.service.freeboard;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.FreeBoard;
import com.example.repository.FreeBoardRepository;

@Service
public class FreeBoardInfoService {

	@Autowired
	private FreeBoardRepository freeBoardRepository;
	
	@Autowired
	HttpSession session;
	
	public FreeBoard getFreeBoardInfo(String stringFreeId) {
		Long freeId = Long.parseLong(stringFreeId);
		
		return freeBoardRepository.findByFreeId(freeId);
	}
	
}
