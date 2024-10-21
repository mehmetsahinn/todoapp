package com.sahin.todoapp.service;

import com.sahin.todoapp.model.Tasks;
import com.sahin.todoapp.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class TasksService {
    @Autowired
    private TasksRepository tasksRepository;

    public List<Tasks> getAllTasks() {
        return tasksRepository.findAll();
    }

    public Tasks createTasks(Tasks tasks) {
        return tasksRepository.save(tasks);
    }

    public Optional <Tasks> getTasksById(Long taskId){
        return  tasksRepository.findById(taskId);
    }
    public Tasks updateTasks(Long id, Tasks updatedTasks) {
        if (tasksRepository.existsById(id)) {
            updatedTasks.setId(id);
            return tasksRepository.save(updatedTasks);
        } else {
            return null;
        }
    }
    public Boolean deleteTasks (long id){
        if (tasksRepository.existsById(id)) {
            tasksRepository.deleteById(id);
            System.out.println("Task silindi: ID = " + id);
            return true;
        }else {
        System.out.println("Task bulunamadı: ID = " + id);
        return false;
    }

    }



}

