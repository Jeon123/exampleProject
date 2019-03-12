package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.FreeBoard;
import com.example.model.FreeBoardCategory;

@Repository
public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long>{
	public FreeBoard findByFreeId(Long freeId);
	
	@Query(value = "SELECT * FROM freeboard WHERE title like %?1%", nativeQuery = true)
	public List<FreeBoard> findByTitle(String title);
	
	@Query(value = "SELECT * FROM freeboard WHERE title like %?1%", nativeQuery = true)
	public List<FreeBoard> findByTitle(String title, Pageable pageable);
	
	@Query(value = "SELECT * FROM freeboard WHERE category =?1 AND title like %?2%", nativeQuery = true)
	public List<FreeBoard> findByTitle(String category, String title);
	
	@Query(value = "SELECT * FROM freeboard WHERE category =?1 AND title like %?2%", nativeQuery = true)
	public List<FreeBoard> findByTitle(String category, String title, Pageable pageable);
	
//	@Query(value="SELECT category, COUNT(category) FROM freeboard GROUP BY category", nativeQuery = true)
//	public List<FreeBoardCategory> findByCategoryAndCount();
} 