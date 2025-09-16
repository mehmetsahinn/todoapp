package com.sahin.todoapp.service;

import com.sahin.todoapp.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    List<Task> getAllTasks();
    Optional<Task> getTaskById(Long taskId);
    Task createTask(Task tasks);
    Task updateTask(Long id, Task updatedTask);
    Task taskDone(Long id);
    boolean deleteTask(long id);



}
