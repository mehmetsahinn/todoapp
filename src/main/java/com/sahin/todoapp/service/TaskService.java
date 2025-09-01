package com.sahin.todoapp.service;

import com.sahin.todoapp.model.Task;
import com.sahin.todoapp.repository.TaskRepository;
import jakarta.validation.constraints.Null;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    public Task createTask(Task tasks) {
        return taskRepository.save(tasks);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id).orElse(null);
        task.setTaskName(
                Optional.ofNullable(updatedTask.getTaskName()).orElse(task.getTaskName()));
        task.setDescription(
                Optional.ofNullable(updatedTask.getDescription()).orElse(task.getDescription()));
        task.setStatus(
                Optional.ofNullable(updatedTask.getStatus()).orElse(task.getStatus()));
        return taskRepository.save(task);
    }

    public Task taskDone(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus(true);
            return taskRepository.save(task);
        }
        return null;
    }

    public Boolean deleteTask(long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    taskRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }

}

