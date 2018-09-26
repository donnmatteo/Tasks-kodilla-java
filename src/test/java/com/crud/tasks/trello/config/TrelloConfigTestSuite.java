package com.crud.tasks.trello.config;

import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloConfigTestSuite {

    @Autowired
    TrelloConfig trelloConfig;

    @Test
    public void testTrelloConfingGetUsername() {
        //Then
        assertEquals("donmatteo1", trelloConfig.getTrelloUsername());
    }

    @Test
    public void testTrelloConfigGetApiEndPoint() {
        //Then
        assertEquals("https://api.trello.com/1", trelloConfig.getTrelloApiEndpoint());
    }

    @Test
    public void testTrelloConfigGetAppKey() {
        //Then
        assertEquals("75ed30e7ce02cc1009af7c3406df61b0", trelloConfig.getTrelloAppKey());
    }

    @Test
    public void testTrelloConfigGetAppToken() {
        //Then
        assertEquals("d57e400ba12f7abb918855d64b51fc74b8f241252746e15178672a4d8bfea52b",
                trelloConfig.getTrelloToken());
    }

}
