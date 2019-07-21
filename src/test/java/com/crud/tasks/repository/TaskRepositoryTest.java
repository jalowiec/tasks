package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import com.crud.tasks.service.DbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TaskRepositoryTest {



    @Autowired
    TaskRepository taskRepository;

    @Test
    public void findAllTest(){
        //Given
        Task taskOne = new Task(1L, "One", "Task one");
        int taskCounterBeforeSave = taskRepository.findAll().size();
        taskRepository.save(taskOne);

        //When
        int taskCounter = taskRepository.findAll().size();

        //Then
        Assert.assertEquals(taskCounterBeforeSave+1, taskCounter);


    }

    @Test
    public void saveTest(){
        //Given
        Task taskOne = new Task(1L, "One", "Task one");

        //When
        taskRepository.save(taskOne);
        Long id = (long) taskRepository.findAll().size() - 1;
        Task savedTask = taskRepository.findOne(id);

        //Then
        Assert.assertNotEquals(null, savedTask);

    }

    @Test
    public void findByIdTest(){
        //Given
        Task taskOne = new Task(1L, "One", "Task one");

        //When
        taskRepository.save(taskOne);
        Long id = (long) taskRepository.findAll().size() - 1;
        Optional<Task> savedTask = taskRepository.findById(id);

        //Then
        Assert.assertNotEquals(null, savedTask);
    }


    @Test
    public void countTest(){
        //Given
        long taskCounterBeforeSave = taskRepository.count();
        Task taskOne = new Task(1L, "One", "Task one");
        Task taskTwo = new Task(2L, "Two", "Task two");
        taskRepository.save(taskOne);
        taskRepository.save(taskTwo);

        //When
        long taskCounter = taskRepository.count();

        //Then
        Assert.assertEquals(taskCounterBeforeSave+2, taskCounter);


    }


}

