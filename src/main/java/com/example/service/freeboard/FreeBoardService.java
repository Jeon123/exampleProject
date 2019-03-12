package com.example.service.freeboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.FreeBoard;
import com.example.repository.FreeBoardRepository;

@Service
public class FreeBoardService {

	@Autowired
	FreeBoardRepository freeBoardRepository;
	
	public FreeBoard write(FreeBoard freeBoard) {
		
		return freeBoardRepository.save(freeBoard);
	}

	public FreeBoard update(FreeBoard freeBoard) {
		
		return freeBoardRepository.save(freeBoard);
	}
	
	public void delete(String StringFreeId) {
		Long freeId = Long.parseLong(StringFreeId);
		
		freeBoardRepository.deleteById(freeId);
	}
	
}
