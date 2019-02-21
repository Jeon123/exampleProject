package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.FreeBoard;

@Repository
public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long>{
	public FreeBoard findByFreeId(Long freeId);
}
