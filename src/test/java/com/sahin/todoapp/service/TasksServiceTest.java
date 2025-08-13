package com.sahin.todoapp.service;

import com.sahin.todoapp.model.Tasks;
import com.sahin.todoapp.repository.TasksRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class TasksServiceTest {

    private TasksService tasksService;
    private TasksRepository tasksRepository;

    @Before
    public void setUp() throws Exception {
        tasksRepository = Mockito.mock(TasksRepository.class);
        tasksService= new TasksService(tasksRepository);
    }

    @Test
    public void whenCreateTaskCallWithValidRequest_itShouldReturnValidTask(){
        Tasks inputTask= Tasks.builder()
                .taskName("taskName")
                .description("Description").build();

        Tasks savedTask =Tasks.builder()
                .id(1l)
                .taskName("taskName")
                .description("Description").build();

        when(tasksRepository.save(inputTask)).thenReturn(savedTask);
        Tasks result = tasksService.createTasks(inputTask);
        assertEquals(Long.valueOf(1L), result.getId());
        assertEquals("taskName", result.getTaskName());
        assertEquals("Description", result.getDescription());
    }

}