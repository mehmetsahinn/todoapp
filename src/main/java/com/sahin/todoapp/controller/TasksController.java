package com.sahin.todoapp.controller;
import com.sahin.todoapp.service.TasksService;
import com.sahin.todoapp.model.Tasks;
import com.sahin.todoapp.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TasksController {
    @Autowired
    private TasksService tasksService;

    @GetMapping
    public List<Tasks> getAllTasks() {
        return tasksService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tasks> getTasksById(@PathVariable Long id) {
        Optional<Tasks> tasks = tasksService.getTasksById(id);
        return tasks.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tasks> createTasks(@RequestBody Tasks tasks) {
        Tasks savedTasks = tasksService.createTasks(tasks);
        return new ResponseEntity<>(savedTasks, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tasks> updateTasks(@PathVariable Long id, @RequestBody Tasks updatedTasks) {
        Tasks tasks = tasksService.updateTasks(id, updatedTasks);
        if (tasks != null) {
            return ResponseEntity.ok(tasks);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        boolean isDeleted = tasksService.deleteTasks(id);
        if (isDeleted) {
            return new ResponseEntity<>("Task silindi: ID = " + id, HttpStatus.OK);
        } else {
            // ID bulunamadıysa 404 NOT FOUND döner
            return new ResponseEntity<>("Task bulunamadı: ID = " + id, HttpStatus.NOT_FOUND);
        }
    }

}

