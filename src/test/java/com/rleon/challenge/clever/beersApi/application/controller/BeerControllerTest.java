package com.rleon.challenge.clever.beersApi.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rleon.challenge.clever.beersApi.AppChallengeUtilsForTest;
import com.rleon.challenge.clever.beersApi.application.dto.request.BeerRequestDto;
import com.rleon.challenge.clever.beersApi.application.dto.response.BeerResponseDto;
import com.rleon.challenge.clever.beersApi.application.facade.BeerFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    private BeerController beerController;

    @MockBean
    private BeerFacade beerFacade;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;


    @BeforeEach
    void setUp() {
        this.beerController = new BeerController(beerFacade);
    }


    @Test
    void itShouldGetAllBeers_200() throws Exception {
        List<BeerResponseDto> beerResponseDtoList = AppChallengeUtilsForTest.createListBeersResponseDto(2);

        //when
        when(beerFacade.findAllBeers()).thenReturn(beerResponseDtoList);
        mockMvc.perform(MockMvcRequestBuilders.get("/beers").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void itShouldCreateBeers_200() throws Exception {
        BeerRequestDto beerRequestDto = AppChallengeUtilsForTest.createBeerRequestDto();
        BeerResponseDto beerResponseDto = AppChallengeUtilsForTest.createBeerResponseDto();

        //when
        beerFacade.createBeer(any());
        mockMvc.perform(MockMvcRequestBuilders.post("/beers").content(mapper.writeValueAsBytes(beerRequestDto)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    void itShouldGetDetails_200() throws Exception {
        BeerResponseDto beerResponseDto = AppChallengeUtilsForTest.createBeerResponseDto();

        //when
        when(beerFacade.getDetailsById(any())).thenReturn(beerResponseDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/beers/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void itShouldGetBoxPrices_200() throws Exception {
        BeerResponseDto beerResponseDto = AppChallengeUtilsForTest.createBeerResponseDto();

        //when
        when(beerFacade.getBeerPrices(any(), any(), any())).thenReturn(beerResponseDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/beers/1/boxprice").param("currency", "USD").param("quantity", "2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }


}
