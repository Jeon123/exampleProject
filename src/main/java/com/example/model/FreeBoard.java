package com.example.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "freeboard")
public class FreeBoard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "freeId")
	private Long freeId;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "title")
	private String title;

	@Column(name = "writer")
	private String writer;
	
	@Column(name = "category")
	private String category;
	
	@CreationTimestamp
	@Column(name = "createTime")
	private LocalDateTime createTime;
	
	@UpdateTimestamp
	@Column(name = "updateTime")
	private LocalDateTime updateTime;
}
