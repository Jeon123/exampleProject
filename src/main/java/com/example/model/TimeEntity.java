package com.example.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class })
@Getter
public abstract class TimeEntity {
	
	@CreatedDate
	@Column(name = "createTime", updatable = false)
	private LocalDateTime createTime = LocalDateTime.now();
	
	@LastModifiedDate
	@Column(name = "updateTime", updatable = true)
	private LocalDateTime updateTime = LocalDateTime.now();
}
