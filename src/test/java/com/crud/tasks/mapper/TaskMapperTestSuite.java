package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTestSuite {

    @Autowired
    TaskMapper taskMapper;

    Task task;
    TaskDto taskDto;
    List<TaskDto> taskDtoList;
    List<Task> taskList;

    @Before
    public void initialize() {
        task = new Task(2L, "Task", "Content of task");
        taskDto = new TaskDto(2L, "Task", "Content of task");
        Task myTask = new Task(3L, "My Task", "Content of my task");
        TaskDto myTaskDto = new TaskDto(3L, "My Task", "Content of my task");
        taskDtoList = Arrays.asList(taskDto, myTaskDto);
        taskList = Arrays.asList(task, myTask);

    }

    @Test
    public void mapToTaskDtoTest(){
        //When
        TaskDto taskDtoActual = taskMapper.mapToTaskDto(task);
        //Then
        assertThat(taskDtoActual, samePropertyValuesAs(taskDto));
    }

    @Test
    public void mapToTaskTest(){
        //When
        Task taskActual = taskMapper.mapToTask(taskDto);
        //Then
        assertThat(taskActual, samePropertyValuesAs(task));
    }

    @Test
    public void mapToTaskDtoListTest(){
        //When
        List<TaskDto> taskDtoListActual = taskMapper.mapToTaskDtoList(taskList);
        //Then
        Assert.assertEquals(taskDtoListActual.size(), taskDtoList.size());
        for(int i=0; i<taskDtoListActual.size(); i++){
            assertThat(taskDtoListActual.get(i), samePropertyValuesAs(taskDtoList.get(i)));

        }
    }

}
