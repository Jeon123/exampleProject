package com.example.service.freeboard;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Category;
import com.example.repository.FreeBoardRepository;
import com.example.repository.CommonRepository;

@Service
public class CommonService {
	
	@Autowired
	FreeBoardRepository freeBoardRepository;
	
	@Autowired
	private CommonRepository freeboardOrder;
	
	@Autowired
	CommonRepository freeBoardOrder;
	
	@Autowired
	HttpSession session;
	
	// Select table(Excel Download)
	public <T> List<T> getFindAll(String tableName) {
		return freeBoardOrder.findAll(tableName);
	}
	
	// Select Category
	public void getsideBarInfo() {
		List<Category> categoryList = freeboardOrder.findByCategoryAndCount();
		
		session.setAttribute("categoryList", categoryList);
	}
}