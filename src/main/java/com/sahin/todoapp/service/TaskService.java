package com.sahin.todoapp.service;

import com.sahin.todoapp.model.Task;
import com.sahin.todoapp.repository.TaskRepository;
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

    public Task updateTask(Long id, Task updatedTasks) {
        if (taskRepository.existsById(id)) {
            updatedTasks.setId(id);
            return taskRepository.save(updatedTasks);
        } else {
            return null;
        }
    }

    public Task taskDone(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            //optionalTask.setStatus(true);
            Task task = optionalTask.get();
            task.setStatus(true);
            return taskRepository.save(task);
        }
        return null;
    }

            public Boolean deleteTask(long id){
            if (taskRepository.existsById(id)) {
                taskRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        }
    }

