package com.bdiplus.task.service;

import java.util.List;

import com.bdiplus.task.model.Task;

public interface TaskService {
	List<Task> getAllTasks();

	Task getTaskById(Long id);

	boolean saveTask(Task task);

	boolean updateTask(Task task);

	boolean deletTaskById(Long id);
	
	boolean updateTaskStatus(Long id);

}
