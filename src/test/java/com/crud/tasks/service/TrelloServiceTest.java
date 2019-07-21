package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {

    @InjectMocks
    TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;


    @Mock
    private AdminConfig adminConfig;



    @Test
    public void fetchTrelloBoardsTest() {

        //Given
        List<TrelloListDto> trelloList = new ArrayList<>();
        trelloList.add(new TrelloListDto("1", "test_list", false));
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("1", "trello board dto1",trelloList));
        trelloBoardDtoList.add(new TrelloBoardDto("2", "trello board dto2",trelloList));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);
        int actualTrelloBoardDtoListSize = trelloService.fetchTrelloBoards().size();

        //When
        trelloBoardDtoList.add(new TrelloBoardDto("3", "trello board dto3",trelloList));

        //Then
        Assert.assertEquals(++actualTrelloBoardDtoListSize, trelloService.fetchTrelloBoards().size());

    }

    @Test
    public void createdTrelloCardTest(){
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("trelloCardDto", "trelloCardDto description", "1", "1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "CreatedTrelloCardDto", "url");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        when(adminConfig.getAdminMail()).thenReturn("test@email.com");

        //When
        CreatedTrelloCardDto actualCreatedTrelloCardDto = trelloService.createdTrelloCard(trelloCardDto);

        //Then
        assertThat(actualCreatedTrelloCardDto, samePropertyValuesAs(createdTrelloCardDto));

    }

}

