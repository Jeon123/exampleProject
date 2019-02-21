package com.example.service.freeboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.FreeBoard;
import com.example.repository.FreeBoardRepository;

@Service
public class FreeBoardWriteService {

	@Autowired
	FreeBoardRepository freeBoardRepository;
	
	private Long returnLongValue(String StringFreeId) {
		return Long.parseLong(StringFreeId);
	}
	
	public void write(String title, String content, String writer) {
		
		FreeBoard freeBoard = new FreeBoard();
		
		freeBoard.setTitle(title);
		freeBoard.setContent(content);
		freeBoard.setWriter(writer);
		
		freeBoardRepository.save(freeBoard);
	}
	
	public void update(String freeId, String title, String content, String writer) {
		
		FreeBoard freeBoard = new FreeBoard();
		
		freeBoard.setFreeId(returnLongValue(freeId));
		freeBoard.setTitle(title);
		freeBoard.setContent(content);
		freeBoard.setWriter(writer);
		
		freeBoardRepository.save(freeBoard);
	}
	
	
}
