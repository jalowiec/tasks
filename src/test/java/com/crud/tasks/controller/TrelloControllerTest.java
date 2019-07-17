package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import com.google.gson.Gson;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrelloController.class)
public class TrelloControllerTest {

    private final TrelloCard trelloCard = new TrelloCard("Trello card", "Trello card description", "1", "2");
    private final TrelloCardDto trelloCardDto = new TrelloCardDto("Trello card", "Trello card description", "1", "2");
    private final CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "createdTrelloCard", "shorturl");
    private static Gson gson = new Gson();
    private final String jsonContent = gson.toJson(trelloCardDto);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TrelloFacade trelloFacade;

    @Test
    public void getTrelloBoardsTest() throws Exception{
        mockMvc.perform(get("/v1/trello/getTrelloBoards")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createTrelloCardTest() throws Exception {
        Mockito.when(trelloFacade.createCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        mockMvc.perform(post("/v1/trello/createTrelloCard")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }



}
