package io.github.cntmin81.mytodo.dto;

import java.io.Serializable;

public class TaskRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2179196924659616238L;
	private Long id;
	private String task;
	private Boolean hasDone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "TaskRequest [id=" + id + ", task=" + task + ", hasDone=" + hasDone + "]";
	}

}
