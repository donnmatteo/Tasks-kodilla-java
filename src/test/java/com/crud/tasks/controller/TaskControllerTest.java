package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private TrelloFacade trelloFacade;


    @Test
    public void testGetTasks() throws Exception {
        TaskDto taskDto1 = new TaskDto(1L, "test title 1", "test content 1");
        List<TaskDto> taskDtoList1 = new ArrayList<>();
        taskDtoList1.add(taskDto1);

        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(taskDtoList1);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("test title 1")))
                .andExpect(jsonPath("$[0].content", is("test content 1")));

    }

    @Test
    public void testGetTask() throws Exception {
        //Given
        TaskDto taskDto1 = new TaskDto(1L, "test title 1", "test content 1");

        when(service.getTask(1L)).thenReturn(Optional.ofNullable(new Task(1L, "test title 2", "content 2")));
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto1);

        //When & Then
        mockMvc.perform(get("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test title 1")))
                .andExpect(jsonPath("$.content", is("test content 1")));

    }

    @Test
    public void testDeleteTask() throws Exception {
        //When & Then
        mockMvc.perform(delete("/v1/tasks/1")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateTask() throws Exception {
        Gson gson = new Gson();
        TaskDto taskDto = new TaskDto(1L, "test title 1", "test content 1");
        String jsonContent = gson.toJson(taskDto);

        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);

        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("test title 1")))
                .andExpect(jsonPath("$.content", is("test content 1")));


    }

    @Test
    public void testCreateTask() throws Exception {
        Gson gson = new Gson();
        TaskDto taskDto = new TaskDto(null, "test title 1", "test content 1");
        TaskDto taskDto1 = new TaskDto(1L, "test title 1", "test content 1");
        String jsonContent = gson.toJson(taskDto);

        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto1);

        mockMvc.perform(post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

    }

}