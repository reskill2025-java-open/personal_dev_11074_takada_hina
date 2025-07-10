package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "titles")
public class Titles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "user_id")
	private Integer userId;

	private String title;

	private LocalDate deadline;

	@Column(name = "title_progress")
	private Integer titleProgress;

	@Column(name = "title_contents")
	private String titleContents;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public LocalDate getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public Integer getTitleProgress() {
		return titleProgress;
	}

	public void setTitleProgress(Integer titleProgress) {
		this.titleProgress = titleProgress;
	}

	public String getTitleContents() {
		return titleContents;
	}

	public void setTitleContents(String titleContents) {
		this.titleContents = titleContents;
	}

}
