package com.crud.tasks.trello.mapper;

import com.crud.tasks.domain.*;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {

    @Autowired
    TrelloMapper trelloMapper;

    List<TrelloList> trelloLists;
    List<TrelloListDto> trelloListDtos;
    TrelloCard trelloCard;
    TrelloCardDto trelloCardDto;
    List<TrelloBoard> trelloBoards;
    List<TrelloBoardDto> trelloBoardDtos;

    @Before
    public void initialize() {
        trelloCardDto = new TrelloCardDto("TrelloCard", "TrelloCardDesc", "1", "2");
        trelloCard = new TrelloCard("TrelloCard", "TrelloCardDesc", "1", "2");

        TrelloList trelloListDone = new TrelloList("1", "Done", true);
        TrelloList trelloListToDo = new TrelloList("2", "ToDo", false);
        TrelloList trelloListDoing = new TrelloList("3", "Doing", false);
        trelloLists = new ArrayList<>(Arrays.asList(trelloListDone, trelloListToDo, trelloListDoing));

        TrelloListDto trelloListDtoDone = new TrelloListDto("1", "Done", true);
        TrelloListDto trelloListDtoToDo = new TrelloListDto("2", "ToDo", false);
        TrelloListDto trelloListDtoDoing = new TrelloListDto("3", "Doing", false);
        trelloListDtos = new ArrayList<>(Arrays.asList(trelloListDtoDone, trelloListDtoToDo, trelloListDtoDoing));

        TrelloBoard trelloBoard = new TrelloBoard("1", "Board", trelloLists);
        TrelloBoard myTrelloBoard = new TrelloBoard("2", "MyBoard", trelloLists);
        TrelloBoardDto trelloBoardDto =  new TrelloBoardDto("1", "Board", trelloListDtos);
        TrelloBoardDto myTrelloBoardDto =  new TrelloBoardDto("2", "MyBoard", trelloListDtos);

        trelloBoards = new ArrayList<>(Arrays.asList(trelloBoard, myTrelloBoard));
        trelloBoardDtos = new ArrayList<>(Arrays.asList(trelloBoardDto, myTrelloBoardDto));
    }
    @Test
    public void mapToBoardsTest(){

        //When
        List<TrelloBoard> trelloBoardActual = trelloMapper.mapToBoards(trelloBoardDtos);

        //Then
        Assert.assertEquals(trelloBoardActual.size(), trelloBoards.size());
        for(int i=0; i<trelloBoardActual.size(); i++){
            assertThat(trelloBoardActual.get(i), hasProperty("id", equalTo(trelloBoards.get(i).getId())));
            assertThat(trelloBoardActual.get(i), hasProperty("name", equalTo(trelloBoards.get(i).getName())));
            Assert.assertEquals(trelloBoardActual.get(i).getLists().size(), trelloBoards.get(i).getLists().size());
            for(int j=0; j<trelloBoardActual.get(i).getLists().size(); j++){
                assertThat(trelloBoardActual.get(i).getLists().get(j), samePropertyValuesAs(trelloBoards.get(i).getLists().get(j)));
            }
        }

    }

    @Test
    public void mapToBoardsDtoTest(){

        //When
        List<TrelloBoardDto> trelloBoardDtoActual = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        Assert.assertEquals(trelloBoardDtoActual.size(), trelloBoardDtos.size());
        for(int i=0; i<trelloBoardDtoActual.size(); i++){
            assertThat(trelloBoardDtoActual.get(i), hasProperty("id", equalTo(trelloBoardDtos.get(i).getId())));
            assertThat(trelloBoardDtoActual.get(i), hasProperty("name", equalTo(trelloBoardDtos.get(i).getName())));
            Assert.assertEquals(trelloBoardDtoActual.get(i).getLists().size(), trelloBoardDtos.get(i).getLists().size());
            for(int j=0; j<trelloBoardDtoActual.get(i).getLists().size(); j++){
                assertThat(trelloBoardDtoActual.get(i).getLists().get(j), samePropertyValuesAs(trelloBoardDtos.get(i).getLists().get(j)));
            }
        }

    }

    @Test
    public void mapToListTest(){

        //When
        List<TrelloList> trelloListActual = trelloMapper.mapToList(trelloListDtos);

        //Than
        Assert.assertEquals(trelloListActual.size(), trelloLists.size());
        for(int i=0; i<trelloListActual.size(); i++){
            assertThat(trelloListActual.get(i), samePropertyValuesAs(trelloLists.get(i)));
        }
    }

    @Test
    public void mapToListDtoTest(){

        //When
        List<TrelloListDto> trelloListDtosActual = trelloMapper.mapToListDto(trelloLists);

        //Than
        Assert.assertEquals(trelloListDtosActual.size(), trelloListDtos.size());
        for(int i=0; i<trelloListDtosActual.size(); i++){
            assertThat(trelloListDtosActual.get(i), samePropertyValuesAs(trelloListDtos.get(i)));
        }
     }

    @Test
    public void mapToCardDtoTest() {
        // When
        TrelloCardDto trelloCardDtoActual = trelloMapper.mapToCardDto(trelloCard);

        // Then
        assertThat(trelloCardDtoActual, samePropertyValuesAs(trelloCardDto));

    }

    @Test
    public void mapToCardTest() {
        // When
        TrelloCard trelloCardActual = trelloMapper.mapToCard(trelloCardDto);

        // Then
        assertThat(trelloCardActual, samePropertyValuesAs(trelloCard));

    }
}
