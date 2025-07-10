package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "title_id")
	private Integer titleId;

	@Column(name = "task_progress")
	private Integer taskProgress;

	@Column(name = "task_title")
	private String taskTitle;

	public Task() {

	}

	public Task(Integer titleId, Integer taskProgress, String taskTitle) {

		this.titleId = titleId;
		this.taskProgress = taskProgress;
		this.taskTitle = taskTitle;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public Integer getTaskProgress() {
		return taskProgress;
	}

	public void setTaskProgress(Integer taskProgress) {
		this.taskProgress = taskProgress;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

}
