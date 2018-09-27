package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DbServiceTestSuite {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void testGetAllTasks() {
        //Given
        Task task1 = new Task(1L, "title 1", "content 1");
        List<Task> taskList1 = new ArrayList<>();
        taskList1.add(task1);

        when(repository.findAll()).thenReturn(taskList1);

        //When
        List<Task> testList = dbService.getAllTasks();
        //Then
        assertEquals(1, testList.size());
    }

    @Test
    public void testSaveTask() {
        //Given
        Task task1 = new Task(1L, "title 1", "content 1");

        when(repository.save(task1)).thenReturn(task1);

        //When
        Task testTask = dbService.saveTask(task1);
        //Then
        assertEquals("title 1", testTask.getTitle());
    }

    @Test
    public void testGetTask() {
        //Given
        Task task1 = new Task(1L, "title 1", "content 1");

        when(repository.findById(1L)).thenReturn(Optional.ofNullable(task1));

        //When
        Optional<Task> testTask = dbService.getTask(1L);
        //Then
        assertEquals("title 1", testTask.orElse(null).getTitle());
    }

    @Test
    public void testDeleteTask() {
        //When
        dbService.deleteTask(1L);
        //Then
        verify(repository, times(1)).delete(1L);
    }

}
