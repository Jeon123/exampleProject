package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.model.Comment;
import com.example.service.comment.CommentService;

@RestController
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	private Long returnLongValue(String stringToLong) {
		return Long.parseLong(stringToLong);
	}
	
	@GetMapping("/commentList/{boardId}")
	public List<Comment> commentList(@PathVariable(value="boardId") String boardId) {
		return commentService.commentList(boardId);	
	}
	
	@PostMapping("/commentInsert")
	public Comment commentInsert(@RequestBody Comment comment) {
		return commentService.insert(comment);
	}
	
	@PostMapping("/commentUpdate/{boardId}/{commentId}")
	public Comment commentUpdate(@RequestBody Comment comment) {
		
		return commentService.update(comment);
	}
	
	@GetMapping("/commentDelete/{boardId}/{commentId}")
	public ModelAndView commentDelete(@PathVariable(value="boardId") String boardId, @PathVariable(value="commentId") String commentId) {
		commentService.delete(returnLongValue(commentId));
		
		return new ModelAndView(new RedirectView("/"+boardId));
	}
}