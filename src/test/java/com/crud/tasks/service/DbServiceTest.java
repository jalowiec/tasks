package com.crud.tasks.service;


import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    DbService dbService;

    @Mock
    private TaskRepository taskRepository;


    private final Task taskOne = new Task(1L, "One", "Task one");
    private final Task taskTwo = new Task(2L, "Two", "Task two");
    private final Task taskThree = new Task(3L, "Three", "Task three");


    @Test
    public void getAllTaskTest(){


        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(taskOne);
        taskList.add(taskTwo);
        taskList.add(taskThree);
        when(taskRepository.findAll()).thenReturn(taskList);

        //When
        List<Task> actualTaskList = dbService.getAllTasks();

        //Then
        Assert.assertEquals(actualTaskList, taskList);


    }

    @Test
    public void getTaskByIdTest(){

        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(taskOne);
        taskList.add(taskTwo);
        taskList.add(taskThree);
        when(taskRepository.findById(3L)).thenReturn(java.util.Optional.ofNullable(taskThree));

        //When
        Optional<Task> actualTask = dbService.getTaskById(3L);

        //Then
        assertThat(actualTask, samePropertyValuesAs(java.util.Optional.ofNullable(taskThree)));

    }
    @Test
    public void saveTaskTest(){

        //Given
        when(taskRepository.save(taskOne)).thenReturn(taskOne);

        //When
        Task actualTask = dbService.saveTask(taskOne);

        //Then
        assertThat(actualTask, samePropertyValuesAs(taskOne));

    }



}
