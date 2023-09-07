package com.bdiplus.task.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdiplus.task.model.Task;
import com.bdiplus.task.repository.TaskRepository;
import com.bdiplus.task.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository repository;

	@Override
	public List<Task> getAllTasks() {
		List<Task> tasks = repository.findAll();
		return tasks;
	}

	@Override
	public Task getTaskById(Long id) {

		return repository.findById(id).get();
	}

	@Override
	public boolean saveTask(Task task) {
		Task addTask = repository.save(task);

		if (getTaskById(addTask.getId()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateTask(Task task) {

		Optional<Task> findById = repository.findById(task.getId());
		if (findById.isPresent()) {
			repository.save(task);
			return true;
		}
		return false;
	}

	@Override
	public boolean deletTaskById(Long id) {
		repository.deleteById(id);

		if (repository.findById(id).isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateTaskStatus(Long id) {
		Task taskById = getTaskById(id);
		taskById.setStatus("Completed");
		return saveTask(taskById);
	}

}
