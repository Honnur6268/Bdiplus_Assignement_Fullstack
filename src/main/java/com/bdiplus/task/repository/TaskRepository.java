package com.bdiplus.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bdiplus.task.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
