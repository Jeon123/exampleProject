package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.example.model.Category;

@Repository
@SuppressWarnings("unchecked")
public class CommonRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	
	public List<Category> findByCategoryAndCount() {
		String sql = "SELECT category, COUNT(category) FROM FreeBoard GROUP BY category";
		
		List<Object[]> result = em.createQuery(sql).getResultList();
		
		List<Category> categoryList = new ArrayList<Category>();
		
		for(Object[] ob : result) {
			Category category = new Category();
			
		    category.setCategory((String) ob[0]);
		    category.setCount(((Number) ob[1]).toString());
		    
		    categoryList.add(category);
		}
		
		return categoryList;
	}
	
	public <T> List<T> findAll(String tableName){
		String sql = "FROM " + tableName;
		
		List<T> result = em.createQuery(sql).getResultList();
		
		return result;
	}
}