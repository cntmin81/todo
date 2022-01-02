package io.github.cntmin81.mytodo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String task;
	private Boolean hasDone;

	public Task() {
	}

	public Task(String task) {
		this.task = task;
	}
	
	public Task(String task, Boolean hasDone) {
		this.task = task;
		this.hasDone = hasDone;
	}

	public Long getId() {
		return id;
	}
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Boolean getHasDone() {
		return hasDone;
	}

	public void setHasDone(Boolean hasDone) {
		this.hasDone = hasDone;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", task=" + task + ", hasDone=" + hasDone + "]";
	}
}
