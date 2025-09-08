package com.sahin.todoapp.controller;
import com.sahin.todoapp.service.serviceImpl.TaskServiceImpl;
import com.sahin.todoapp.model.Task;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskServiceImpl taskServiceImpl;

    @GetMapping
    @Operation(
            description = "Get all tasks",
            responses = {
                    @ApiResponse(responseCode = "400", ref = "badRequest"),
                    @ApiResponse(responseCode = "500", ref = "internalServerError"),
                    @ApiResponse(responseCode = "200", ref = "successfulResponse")
            }
    )
    public List<Task> getAllTasks() {
        return taskServiceImpl.getAllTasks();
    }

    @GetMapping("/{id}")
    @Operation(
            description = "Get task by id",
            responses = {
                    @ApiResponse(responseCode = "400", ref = "badRequest"),
                    @ApiResponse(responseCode = "500", ref = "internalServerError"),
                    @ApiResponse(responseCode = "200", ref = "successfulResponse")
            }
    )
    public ResponseEntity<Task> getTasksById(@PathVariable Long id) {
        Optional<Task> tasks = taskServiceImpl.getTaskById(id);
        return tasks.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            description = "Create new task",
            responses = {
                    @ApiResponse(responseCode = "400", ref = "badRequest"),
                    @ApiResponse(responseCode = "500", ref = "internalServerError"),
                    @ApiResponse(responseCode = "200", ref = "successfulResponse")
            }
    )
    public ResponseEntity<Task> createTasks(@RequestBody Task tasks) {
        Task savedTasks = taskServiceImpl.createTask(tasks);
        return new ResponseEntity<>(savedTasks, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            description = "Update task by id",
            responses = {
                    @ApiResponse(responseCode
                            = "400", ref = "badRequest"),
                    @ApiResponse(responseCode
                            = "500", ref = "internalServerError"),
                    @ApiResponse(responseCode
                            = "200", ref = "successfulResponse")
            }
    )
    public ResponseEntity<Task> updateTasks(@PathVariable Long id, @RequestBody Task updatedTasks) {
        Task tasks = taskServiceImpl.updateTask(id, updatedTasks);
        if (tasks != null) {
            return ResponseEntity.ok(tasks);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/done")
    @Operation(
            description = "Task done",
            responses = {
                    @ApiResponse(responseCode = "500", ref = "internalServerError"),
                    @ApiResponse(responseCode = "200", ref = "successfulResponse")
            }
    )
    public ResponseEntity<Task> taskDone(@PathVariable Long id) {
        Task tasks = taskServiceImpl.taskDone(id);
        if (tasks != null) {
            return ResponseEntity.ok(tasks);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    @Operation(
            description = "Delete task by id",
            responses = {
                    @ApiResponse(responseCode = "400", ref = "badRequest"),
                    @ApiResponse(responseCode = "500", ref = "internalServerError")
            }
    )
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        boolean isDeleted = taskServiceImpl.deleteTask(id);
        if (isDeleted) {
            return new ResponseEntity<>("Kullanıcı silindi ID = " + id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Task bulunamadı: ID = " + id, HttpStatus.NOT_FOUND);
        }
    }

}

