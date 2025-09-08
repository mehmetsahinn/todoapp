package com.sahin.todoapp.service;

import com.sahin.todoapp.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> getAllTasks();
    Optional<Task> getTaskById(Long taskId);

}
