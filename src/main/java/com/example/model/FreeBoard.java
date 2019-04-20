package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "freeboard")
public class FreeBoard extends TimeEntity implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "freeId")
	private Long freeId;
	
	@Column(name = "content", length=9999)
	private String content;
	
	@NotNull
	@Column(name = "title")
	private String title;

	@Column(name = "writer")
	private String writer;
	
	@NotNull
	@Column(name = "category")
	private String category;
}