package com.crud.tasks.controller;


import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TaskControllerTest {

    @Autowired
    TaskController taskController;


    @Test
    public void addTaskTest(){
        //Given
        int taskDtoListSize = taskController.getTasks().size();

        //When
        taskController.createTask(new TaskDto(1L, "Task", "Task content"));

        //Then
        assertEquals(++taskDtoListSize, taskController.getTasks().size());

    }

    @Test
    public void deleteTaskTest(){
        //Given
        taskController.createTask(new TaskDto(12L, "Task", "Task content"));
        int taskDtoListSize = taskController.getTasks().size();

        //When
        taskController.deleteTask(12L);

        //Then
        assertEquals(--taskDtoListSize, taskController.getTasks().size());

    }
    @Test
    public void updateTaskTest() throws TaskNotFoundException {
        //Given
        taskController.createTask(new TaskDto(13L, "Task", "Task content"));
        TaskDto updatedTaskDto = new TaskDto(13L, "Updated task", "Updated task content");

        //When
        taskController.updateTask(updatedTaskDto);

        //Then
        assertThat(updatedTaskDto, samePropertyValuesAs(taskController.getTask(13L)));

    }

}
