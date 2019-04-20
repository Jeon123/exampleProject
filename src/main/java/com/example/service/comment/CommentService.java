package com.example.service.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Comment;
import com.example.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	
	public List<Comment> commentList(String boardId) {
		return commentRepository.findByBoardIdOrderByCommentIdDesc(boardId);
	}
	
	public Comment insert(Comment comment) {
		return commentRepository.save(comment);
	}
	
	public Comment update(Comment comment) {
		return commentRepository.save(comment);
	}
	
	public void delete(Long commentId) {
		commentRepository.deleteById(commentId);
	}
}