package io.github.cntmin81.mytodo.dto;

import java.io.Serializable;

public class TaskRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2179196924659616238L;
	private String task;

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	@Override
	public String toString() {
		return "TaskRequest [task=" + task + "]";
	}
}
