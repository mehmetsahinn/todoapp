package com.sahin.todoapp.service;

import com.sahin.todoapp.model.Task;
import com.sahin.todoapp.repository.TaskRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class TasksServiceTest {

    private TaskService taskService;
    private TaskRepository taskRepository;

    @Before
    public void setUp() throws Exception {
        taskRepository = Mockito.mock(TaskRepository.class);
        taskService = new TaskService(taskRepository);
    }

    @Test
    public void whenGetAllTasks_ShouldReturnAllTasks() {
        // Arrange: test verisi oluştur
        Task task1 = Task.builder()
                .id(1L)
                .taskName("Task 1")
                .status(false)
                .description("Desc 1")
                .build();

        Task task2 = Task.builder()
                .id(2L)
                .taskName("Task 2")
                .status(true)
                .description("Desc 2")
                .build();

        List<Task> taskList = Arrays.asList(task1, task2);
        when(taskRepository.findAll()).thenReturn(taskList);
        List<Task> result = taskService.getAllTasks();

        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0).getTaskName());
        assertEquals("Task 2", result.get(1).getTaskName());
    }

    @Test
    public void whenCreateTask_itShouldReturnTask(){
        Task inputTask= Task.builder()
                .taskName("taskName")
                .description("Description").build();

        Task savedTask = Task.builder()
                .id(1l)
                .taskName("taskName")
                .description("Description").build();

        when(taskRepository.save(inputTask)).thenReturn(savedTask);
        Task result = taskService.createTask(inputTask);
        assertEquals(Long.valueOf(1L), result.getId());
        assertEquals("taskName", result.getTaskName());
        assertEquals("Description", result.getDescription());
    }

    @Test
    public void whenGetTasksById_Found() {
        Task task = Task.builder()
                .id(1L)
                .taskName("Test Task")
                .status(false)
                .description("Sample description")
                .build();

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Optional<Task> result = taskService.getTaskById(1L);

        assertEquals(true, result.isPresent());
        assertEquals(Long.valueOf(1L), result.get().getId());
        assertEquals("Test Task", result.get().getTaskName());
        assertEquals(false, result.get().getStatus());
        assertEquals("Sample description", result.get().getDescription());
    }

    @Test
    public void whenGetTasksById_NotFound() {
        when(taskRepository.findById(2L)).thenReturn(Optional.empty());
        Optional<Task> result = taskService.getTaskById(2L);
        assertEquals(false, result.isPresent());
    }



}