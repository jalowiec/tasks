package com.crud.tasks.service;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloServiceTest {

    @Autowired
    TrelloService trelloService;

    @Test
    public void fetchTrelloBoardsTest() {

        //Given
        List<TrelloBoardDto> trelloBoardDtoList = trelloService.fetchTrelloBoards();
        List<TrelloBoardDto> actualTrelloBoardDtoList = new ArrayList<>(trelloBoardDtoList);
        int actualTrelloBoardDtoListSize = actualTrelloBoardDtoList.size();

        List<TrelloListDto> trelloList = new ArrayList<>();
        trelloList.add(new TrelloListDto("1", "test_list", false));

        //When
        actualTrelloBoardDtoList.add(new TrelloBoardDto("2", "test", trelloList));

        //Then
        Assert.assertEquals(++actualTrelloBoardDtoListSize, actualTrelloBoardDtoList.size());


    }

}