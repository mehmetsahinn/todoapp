package com.sahin.todoapp.service;

import com.sahin.todoapp.model.Tasks;
import com.sahin.todoapp.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TasksService {
    private final TasksRepository tasksRepository;

    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public List<Tasks> getAllTasks() {
        return tasksRepository.findAll();
    }

    public Optional<Tasks> getTasksById(Long taskId) {
        return tasksRepository.findById(taskId);
    }

    public Tasks createTasks(Tasks tasks) {
        return tasksRepository.save(tasks);
    }

    public Tasks updateTasks(Long id, Tasks updatedTasks) {
        if (tasksRepository.existsById(id)) {
            updatedTasks.setId(id);
            return tasksRepository.save(updatedTasks);
        } else {
            return null;
        }
    }

    public Tasks taskDone(Long id) {
        Optional<Tasks> optionalTask = tasksRepository.findById(id);
        if (optionalTask.isPresent()) {
            //optionalTask.setStatus(true);
            Tasks task = optionalTask.get();
            task.setStatus(true);
            return tasksRepository.save(task);
        }
        return null;
    }

            public Boolean deleteTasks ( long id){
            if (tasksRepository.existsById(id)) {
                tasksRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        }
    }

