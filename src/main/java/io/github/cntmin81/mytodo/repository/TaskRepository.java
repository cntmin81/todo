package io.github.cntmin81.mytodo.repository;

import org.springframework.data.repository.CrudRepository;

import io.github.cntmin81.mytodo.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
	Iterable<Task> findByHasDone(Boolean hasDone);
}
