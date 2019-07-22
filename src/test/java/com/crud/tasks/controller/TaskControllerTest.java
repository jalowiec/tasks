package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void getTasksTest() throws Exception {
        //Given
        TaskDto taskDtoOne = new TaskDto(1L, "Title one", "Content one");
        TaskDto taskDtoTwo = new TaskDto(2L, "Title two", "Content two");
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(taskDtoOne);
        taskDtoList.add(taskDtoTwo);

        Task taskOne = new Task(1L, "Title one", "Content one");
        Task taskTwo = new Task(2L, "Title two", "Content two");
        List<Task> taskList = new ArrayList<>();
        taskList.add(taskOne);
        taskList.add(taskTwo);

        when(service.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Title one")))
                .andExpect(jsonPath("$[0].content", is("Content one")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("Title two")))
                .andExpect(jsonPath("$[1].content", is("Content two")));

    }

    @Test
    public void getTaskTest() throws Exception {
        //Given
        TaskDto taskDtoOne = new TaskDto(1L, "Title one", "Content one");
        Task taskOne = new Task(1L, "Title one", "Content one");

        when(service.getTaskById(1L)).thenReturn(java.util.Optional.of(taskOne));
        when(taskMapper.mapToTaskDto(taskOne)).thenReturn(taskDtoOne);

        //When & Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Title one")))
                .andExpect(jsonPath("$.content", is("Content one")));
    }

    @Test
    public void deleteTaskTest() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1"))
                .andExpect(status().isOk());

    }

    @Test
    public void updateTaskTest() throws Exception {
        //Given
        TaskDto taskDtoOne = new TaskDto(1L, "Title one", "Content one");
        Task taskOne = new Task(1L, "Title one", "Content one");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDtoOne);

        when(service.saveTask(taskOne)).thenReturn(taskOne);
        when(taskMapper.mapToTaskDto(taskOne)).thenReturn(taskDtoOne);
        when(taskMapper.mapToTask(taskDtoOne)).thenReturn(taskOne);

        //When & Then
        mockMvc.perform(put("/v1/task/updateTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.id", is(1)))
                //.andExpect(jsonPath("$.title", is("Title one")))
                //.andExpect(jsonPath("$.content", is("Content one")));
    }

    @Test
    public void createTaskTest() throws Exception {
        //Given
        TaskDto taskDtoOne = new TaskDto(1L, "Title one", "Content one");
        Task taskOne = new Task(1L, "Title one", "Content one");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDtoOne);

        when(service.saveTask(taskOne)).thenReturn(taskOne);
        when(taskMapper.mapToTask(taskDtoOne)).thenReturn(taskOne);

        //When & Then
        mockMvc.perform(post("/v1/task/createTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }


}