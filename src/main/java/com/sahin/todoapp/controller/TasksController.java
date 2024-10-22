package com.sahin.todoapp.controller;
import com.sahin.todoapp.service.TasksService;
import com.sahin.todoapp.model.Tasks;
import com.sahin.todoapp.repository.TasksRepository;
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
public class TasksController {
    @Autowired
    private TasksService tasksService;

    @GetMapping
    @Operation(
            description = "Get all tasks",
            responses = {
                    @ApiResponse(responseCode = "400",ref = "badRequest"),
                    @ApiResponse(responseCode = "500",ref = "internalServerError"),
                    @ApiResponse(responseCode = "200",ref = "successfulResponse")
            }
    )
    public List<Tasks> getAllTasks() {
        return tasksService.getAllTasks();
    }

    @GetMapping("/{id}")
    @Operation(
            description = "Get task by id",
            responses = {
                    @ApiResponse(responseCode = "400",ref = "badRequest"),
                    @ApiResponse(responseCode = "500",ref = "internalServerError"),
                    @ApiResponse(responseCode = "200",ref = "successfulResponse")
            }
    )
    public ResponseEntity<Tasks> getTasksById(@PathVariable Long id) {
        Optional<Tasks> tasks = tasksService.getTasksById(id);
        return tasks.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            description = "Create new task",
            responses = {
                    @ApiResponse(responseCode = "400",ref = "badRequest"),
                    @ApiResponse(responseCode = "500",ref = "internalServerError"),
                    @ApiResponse(responseCode = "200",ref = "successfulResponse")
            }
    )
    public ResponseEntity<Tasks> createTasks(@RequestBody Tasks tasks) {
        Tasks savedTasks = tasksService.createTasks(tasks);
        return new ResponseEntity<>(savedTasks, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
   // @GetMapping("/{id}")
    @Operation(
            description = "Update task by id",
            responses = {
                    @ApiResponse(responseCode = "400",ref = "badRequest"),
                    @ApiResponse(responseCode = "500",ref = "internalServerError"),
                    @ApiResponse(responseCode = "200",ref = "successfulResponse")
            }
    )
    public ResponseEntity<Tasks> updateTasks(@PathVariable Long id, @RequestBody Tasks updatedTasks) {
        Tasks tasks = tasksService.updateTasks(id, updatedTasks);
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
                    @ApiResponse(responseCode = "500",ref = "internalServerError"),
                    @ApiResponse(responseCode = "200",ref = "successfulResponse")
            }
    )
    public ResponseEntity<Tasks> taskDone(@PathVariable Long id) {
        Tasks tasks = tasksService.taskDone(id);
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
                    @ApiResponse(responseCode = "400",ref = "badRequest"),
                    @ApiResponse(responseCode = "500",ref = "internalServerError")
            }
    )
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        boolean isDeleted = tasksService.deleteTasks(id);
        if (isDeleted) {
            return new ResponseEntity<>("Task silindi: ID = " + id, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Task bulunamadı: ID = " + id, HttpStatus.NOT_FOUND);
        }
    }

}

