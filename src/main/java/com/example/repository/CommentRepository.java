package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
	public List<Comment> findByBoardIdOrderByCommentIdDesc(String boardId);
}