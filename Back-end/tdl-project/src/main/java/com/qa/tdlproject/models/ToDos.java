package com.qa.tdlproject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ToDos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto-generates ids
	@Column(name = "todos_id")
	private Long todosId;

	@Column(name = "user_id")
	@NotNull
	private int userId;

	@NotNull
	private String title;

	@NotNull
	private boolean completed;

	public ToDos() { }

	public ToDos(int userId, String title, boolean completed) {
		this.userId = userId;
		this.title = title;
		this.completed = completed;
	}

	public ToDos(Long todosId, int userId, String title, boolean completed) {
		this.todosId = todosId;
		this.userId = userId;
		this.title = title;
		this.completed = completed;
	}

	public Long getTodosId() {
		return todosId;
	}

	public void setTodosId(Long todosId) {
		this.todosId = todosId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}
