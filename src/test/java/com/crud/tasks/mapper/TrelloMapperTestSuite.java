package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.facade.TrelloFacade;
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
public class TrelloMapperTestSuite {

    @Autowired
    TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloListDto> listsDto1 = new ArrayList<>();
        List<TrelloListDto> listsDto2 = new ArrayList<>();
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("Board 1", "test Board 1", listsDto1);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("Board 2", "test Board 2", listsDto2);
        List<TrelloBoardDto> testList = new ArrayList<>();
        testList.add(trelloBoardDto1);
        testList.add(trelloBoardDto2);
        //When
        List<TrelloBoard> testResult = trelloMapper.mapToBoards(testList);
        //Then
        assertEquals(2, testResult.size());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloList> lists1 = new ArrayList<>();
        List<TrelloList> lists2 = new ArrayList<>();
        TrelloBoard trelloBoard1 = new TrelloBoard("Board 1", "test Board 1", lists1);
        TrelloBoard trelloBoard2 = new TrelloBoard("Board 2", "test Board 2", lists2);
        List<TrelloBoard> testList = new ArrayList<>();
        testList.add(trelloBoard1);
        testList.add(trelloBoard2);
        //When
        List<TrelloBoardDto> testResult = trelloMapper.mapToBoardsDto(testList);
        //Then
        assertEquals(2, testResult.size());
    }

    @Test
    public void testMapToList() {
        //Given
        TrelloListDto list1 = new TrelloListDto("1", "test list 1", false);
        TrelloListDto list2 = new TrelloListDto("1", "test list 2", true);
        List<TrelloListDto> testList = new ArrayList<>();
        testList.add(list1);
        testList.add(list2);
        //When
        List<TrelloList> testResult = trelloMapper.mapToList(testList);
        //Then
        assertEquals(2, testResult.size());
    }

    @Test
    public void testMapToListDto() {
        //Given
        TrelloList list1 = new TrelloList("1", "test list 1", false);
        TrelloList list2 = new TrelloList("1", "test list 2", true);
        List<TrelloList> testList = new ArrayList<>();
        testList.add(list1);
        testList.add(list2);
        //When
        List<TrelloListDto> testResult = trelloMapper.mapToListDto(testList);
        //Then
        assertEquals(2, testResult.size());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto card1 = new TrelloCardDto("1", "test card 1", "11", "id1");
        TrelloCardDto card2 = new TrelloCardDto("2", "test card 2", "22", "id2");
        //When
        TrelloCard testResult = trelloMapper.mapToCard(card1);
        //Then
        assertEquals("test card 1", testResult.getDescription());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard card1 = new TrelloCard("1", "test card 1", "11", "id1");
        TrelloCard card2 = new TrelloCard("2", "test card 2", "22", "id2");
        //When
        TrelloCardDto testResult = trelloMapper.mapToCardDto(card1);
        //Then
        assertEquals("test card 1", testResult.getDescription());
    }
}
