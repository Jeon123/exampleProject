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
	
	public String getFreeBoardInfo(String stringFreeId) {
		Long freeId = Long.parseLong(stringFreeId);
		
		FreeBoard freeBoard = freeBoardRepository.findByFreeId(freeId);
		
		if(freeBoard == null) {
			return "freeBoard";
		}
		
		session.setAttribute("freeBoard", freeBoard);
		
		return "freeBoardInfo";
	}
	
}
