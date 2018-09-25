package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TaskMapperTestSuite {

    @Autowired
    TaskMapper taskMapper;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto task1 = new TaskDto(1L, "title 1", "content 1");
        //when
        Task testResult = taskMapper.mapToTask(task1);
        //Then
        assertEquals("title 1", testResult.getTitle());
    }

    @Test
    public void testMapToTaskDto() {
        //Given
        Task task1 = new Task(1L, "title 1", "content 1");
        //when
        TaskDto testResult = taskMapper.mapToTaskDto(task1);
        //Then
        assertEquals("title 1", testResult.getTitle());
    }

    @Test
    public void testMapToTaskDtoList() {
        //Given
        Task task1 = new Task(1L, "title 1", "content 1");
        List<Task> testList = new ArrayList<>();
        testList.add(task1);
        //When
        List<TaskDto> testResult = taskMapper.mapToTaskDtoList(testList);
        //Then
        assertEquals(1, testResult.size());
    }
}
