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

	@NotNull
	private String title;

	@NotNull
	private boolean completed;

	public ToDos() { }

	public ToDos(String title, boolean completed) {
		this.title = title;
		this.completed = completed;
	}

	public ToDos(Long todosId, String title, boolean completed) {
		this.todosId = todosId;
		this.title = title;
		this.completed = completed;
	}

	public Long getTodosId() {
		return todosId;
	}

	public void setTodosId(Long todosId) {
		this.todosId = todosId;
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
