package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Data
@Table(name="comment")
public class Comment extends TimeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "commentId")
	private Long commentId;
	
	@Column(name = "boardId")
	private String boardId;
	
	@Column(name = "content", length=999)
	private String content;
	
	@Column(name = "writer")
	private String writer;
}