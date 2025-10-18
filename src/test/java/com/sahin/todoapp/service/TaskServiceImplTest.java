package com.sahin.todoapp.service;

import com.sahin.todoapp.model.Task;
import com.sahin.todoapp.repository.TaskRepository;
import com.sahin.todoapp.service.impl.TaskServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


public class TaskServiceImplTest {

    private TaskServiceImpl taskServiceImpl;
    private TaskRepository taskRepository;
    private Task sampleTask1;
    private Task sampleTask2;
    private Task sampleTask3;

    @Before
    public void setUp() throws Exception {
        taskRepository = Mockito.mock(TaskRepository.class);

        taskServiceImpl = new TaskServiceImpl(taskRepository);

        sampleTask1 = Task.builder().id(1L).taskName("Task 1").status(false).description("Desc 1").build();

        sampleTask2 = Task.builder().id(2L).taskName("Task 2").status(true).description("Desc 2").build();

        sampleTask3 = Task.builder().id(3L).taskName("Task 3").status(true).description("Desc 3").build();
    }

    @Test
    public void should_ReturnAllTasks_When_TasksExist() {
        List<Task> taskList = Arrays.asList(sampleTask1, sampleTask2);
        when(taskRepository.findAll()).thenReturn(taskList);
        List<Task> result = taskServiceImpl.getAllTasks();

        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0).getTaskName());
        assertEquals("Task 2", result.get(1).getTaskName());
    }

    @Test
    public void should_ReturnEmptyList_When_NoTasksExist() {
        when(taskRepository.findAll()).thenReturn(Collections.emptyList());
        List<Task> result = taskServiceImpl.getAllTasks();
        assertTrue(result.isEmpty());
    }

    @Test
    public void should_ReturnTask_When_CreateTask() {
        when(taskRepository.save(sampleTask1)).thenReturn(sampleTask1);
        Task result = taskServiceImpl.createTask(sampleTask1);

        assertEquals(Long.valueOf(1L), result.getId());
        assertEquals("Task 1", result.getTaskName());
        assertEquals("Desc 1", result.getDescription());
    }

    @Test
    public void should_ReturnTask_When_GetTasksById_Found() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(sampleTask1));
        Optional<Task> result = taskServiceImpl.getTaskById(1L);

        assertEquals(true, result.isPresent());
        assertEquals(Long.valueOf(1L), result.get().getId());
        assertEquals("Task 1", result.get().getTaskName());
        assertEquals(false, result.get().getStatus());
        assertEquals("Desc 1", result.get().getDescription());
    }

    @Test
    public void should_ReturnFalse_When_GetTasksById_NotFound() {
        when(taskRepository.findById(2L)).thenReturn(Optional.empty());
        Optional<Task> result = taskServiceImpl.getTaskById(2L);
        assertEquals(false, result.isPresent());
    }

    @Test
    public void should_ReturnTaskWithUpdatedName_When_OnlyNameProvided() {
        Long taskId = 1L;
        Task existingTask = new Task();
        existingTask.setId(taskId);
        existingTask.setTaskName("Old Name");
        existingTask.setDescription("Old Description");
        existingTask.setStatus(false);

        Task updatedTask = new Task();
        updatedTask.setTaskName("New Name");

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Task result = taskServiceImpl.updateTask(taskId, updatedTask);

        assertEquals("New Name", result.getTaskName());
        assertEquals("Old Description", result.getDescription());
        assertEquals(false, result.getStatus());
    }

    @Test
    public void should_ReturnTaskWithUpdatedDescription_When_OnlyDescriptionIsProvided() {
        Long taskId = 2L;
        Task existingTask = new Task();
        existingTask.setId(taskId);
        existingTask.setTaskName("Old Name");
        existingTask.setDescription("Old Description");
        existingTask.setStatus(false);

        Task updatedTask = new Task();
        updatedTask.setDescription("New Description");

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Task result = taskServiceImpl.updateTask(taskId, updatedTask);

        assertEquals("Old Name", result.getTaskName());
        assertEquals("New Description", result.getDescription());
        assertEquals(false, result.getStatus());
    }

    @Test
    public void should_ReturnTaskWithUpdatedStatus_WhenOnlyStatusProvided() {
        Long taskId = 3L;
        Task existingTask = new Task();
        existingTask.setId(taskId);
        existingTask.setTaskName("Old Name");
        existingTask.setDescription("Old Description");
        existingTask.setStatus(false);

        Task updatedTask = new Task();
        updatedTask.setStatus(true);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Task result = taskServiceImpl.updateTask(taskId, updatedTask);

        assertEquals("Old Name", result.getTaskName());
        assertEquals("Old Description", result.getDescription());
        assertEquals(true, result.getStatus());
    }

    @Test
    public void should_ReturnUpdatedTask_When_AllFieldsProvided() {
        Long taskId = 4L;
        Task existingTask = new Task();
        existingTask.setId(taskId);
        existingTask.setTaskName("Old Name");
        existingTask.setDescription("Old Description");
        existingTask.setStatus(false);

        Task updatedTask = new Task();
        updatedTask.setTaskName("New Name");
        updatedTask.setDescription("New Description");
        updatedTask.setStatus(true);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Task result = taskServiceImpl.updateTask(taskId, updatedTask);

        assertEquals("New Name", result.getTaskName());
        assertEquals("New Description", result.getDescription());
        assertEquals(true, result.getStatus());
    }

    @Test

    public void should_ReturnTaskWithUpdatedStatusAndName_When_StatusAndNameProvided() {
        Long taskId = 5L;
        Task existingTask = new Task();
        existingTask.setId(taskId);
        existingTask.setTaskName("Old Name");
        existingTask.setDescription("Old Description");
        existingTask.setStatus(false);

        Task updatedTask = new Task();
        updatedTask.setTaskName("New Name");
        updatedTask.setStatus(true);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Task result = taskServiceImpl.updateTask(taskId, updatedTask);

        assertEquals("New Name", result.getTaskName());
        assertEquals("Old Description", result.getDescription());
        assertEquals(true, result.getStatus());
    }

    @Test
    public void should_ReturnTaskWithUpdatedDescriptionAndName_When_DescriptionAndNameProvided() {
        Long taskId = 6L;
        Task existingTask = new Task();
        existingTask.setId(taskId);
        existingTask.setTaskName("Old Name");
        existingTask.setDescription("Old Description");
        existingTask.setStatus(false);

        Task updatedTask = new Task();
        updatedTask.setTaskName("New Name");
        updatedTask.setDescription("New Description");

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Task result = taskServiceImpl.updateTask(taskId, updatedTask);

        assertEquals("New Name", result.getTaskName());
        assertEquals("New Description", result.getDescription());
        assertEquals(false, result.getStatus());
    }

    @Test
    public void should_ReturnTaskWithUpdatedStatusAndDescription_When_StatusAndDescriptionProvided() {
        Long taskId = 7L;
        Task existingTask = new Task();
        existingTask.setId(taskId);
        existingTask.setTaskName("Old Name");
        existingTask.setDescription("Old Description");
        existingTask.setStatus(false);

        Task updatedTask = new Task();
        updatedTask.setDescription("New Description");
        updatedTask.setStatus(true);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Task result = taskServiceImpl.updateTask(taskId, updatedTask);

        assertEquals("Old Name", result.getTaskName());
        assertEquals("New Description", result.getDescription());
        assertEquals(true, result.getStatus());
    }

    @Test
    public void should_ReturnNull_When_TaskNotFound() {
        Long taskId = 99L;
        Task updatedTask = new Task();
        updatedTask.setTaskName("New Name");

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());
        Task result = taskServiceImpl.updateTask(taskId, updatedTask);

        assertNull(result);
        verify(taskRepository, never()).save(any(Task.class)); // save hiç çağrılmamalı
    }


    @Test
    public void should_ReturnTaskWithStatusTrueAndSave_When_TaskExists() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(sampleTask1));
        when(taskRepository.save(sampleTask1)).thenReturn(sampleTask1);
        Task result = taskServiceImpl.taskDone(1L);

        assertEquals(true, result.getStatus());
        verify(taskRepository, times(1)).save(sampleTask1);
    }

    @Test
    public void should_ReturnNull_When_TaskNotExists() {
        Long taskId = 2L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());
        Task result = taskServiceImpl.taskDone(taskId);

        assertEquals(null, result);
        verify(taskRepository, never()).save(any(Task.class));
    }

    @Test

    public void should_ReturnTrueAndDeleteTask_When_TaskExists() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(sampleTask1));
        Boolean result = taskServiceImpl.deleteTask(1l);
        assertEquals(true, result);
        verify(taskRepository, times(1)).deleteById(1l);
    }

    @Test
    public void should_ReturnFalse_When_TaskNotExists() {
        long taskId = 2L;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());
        Boolean result = taskServiceImpl.deleteTask(taskId);

        assertEquals(false, result);
        verify(taskRepository, never()).deleteById(anyLong());
    }
}