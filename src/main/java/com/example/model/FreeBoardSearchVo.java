package com.example.model;

import java.util.List;

import com.example.pageMaker.PageMaker;

import lombok.Data;

@Data
public class FreeBoardSearchVo {

	private List<FreeBoard> freeBoardList;
	
	private PageMaker pageMaker;
	
	private List<FreeBoardCategory> category;
}