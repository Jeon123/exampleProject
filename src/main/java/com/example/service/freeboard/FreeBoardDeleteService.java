package com.example.service.freeboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.FreeBoard;
import com.example.repository.FreeBoardRepository;

@Service
public class FreeBoardDeleteService {

	@Autowired
	FreeBoardRepository freeBoardRepository;
	
	public void delete(String StringFreeId) {
		Long freeId = Long.parseLong(StringFreeId);
		
		freeBoardRepository.deleteById(freeId);
	}
}
