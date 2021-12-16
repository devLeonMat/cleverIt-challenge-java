package com.rleon.challenge.clever.beersApi.application.facade;

import com.rleon.challenge.clever.beersApi.AppChallengeUtilsForTest;
import com.rleon.challenge.clever.beersApi.application.dto.request.BeerRequestDto;
import com.rleon.challenge.clever.beersApi.application.dto.response.BeerResponseDto;
import com.rleon.challenge.clever.beersApi.application.mapper.BeerMapper;
import com.rleon.challenge.clever.beersApi.persistence.entity.Beer;
import com.rleon.challenge.clever.beersApi.service.BeerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BeerFacadeTest {

    private BeerFacade beerFacade;

    @Mock
    private BeerService beerService;

    @Mock
    private BeerMapper beerMapper;

    @BeforeEach
    void setUp() {
        this.beerFacade = new BeerFacade(beerService, beerMapper);
    }

    @Test
    void findAllBeers_test() {
        List<Beer> beerList = AppChallengeUtilsForTest.createListBeers(2);
        List<BeerResponseDto> beerResponseDtoList = AppChallengeUtilsForTest.createListBeersResponseDto(2);
        BeerResponseDto responseDto = AppChallengeUtilsForTest.createBeerResponseDto();

        //when
        when(beerService.getAllBeers()).thenReturn(beerList);
        when(beerMapper.toDto(any(Beer.class))).thenReturn(responseDto);
        List<BeerResponseDto> response = beerFacade.findAllBeers();

        //then
        verify(beerService, times(1)).getAllBeers();
        assertThat(response).isNotNull().usingRecursiveComparison().isEqualTo(beerResponseDtoList);
    }

    @Test
    void createBeer_test() {
        BeerRequestDto beerRequestDto = AppChallengeUtilsForTest.createBeerRequestDto();
        Beer requestEntity = AppChallengeUtilsForTest.createBeerEntity();

        //when
        doNothing().when(beerService).createBeer(any());
        when(beerMapper.toEntity(any(BeerRequestDto.class))).thenReturn(requestEntity);
        beerFacade.createBeer(beerRequestDto);

        //then
        verify(beerService, times(1)).createBeer(requestEntity);
    }

    @Test
    void getDetailsById_test() {
        BeerResponseDto responseDto = AppChallengeUtilsForTest.createBeerResponseDto();
        Beer requestEntity = AppChallengeUtilsForTest.createBeerEntity();
        Beer responseEntity = AppChallengeUtilsForTest.createBeerEntity();

        //when
        when(beerService.getBeerDetails(anyLong())).thenReturn(responseEntity);
        when(beerMapper.toDto(any(Beer.class))).thenReturn(responseDto);
        BeerResponseDto response = beerFacade.getDetailsById(1L);

        //then
        verify(beerService, times(1)).getBeerDetails(1L);
        assertThat(response).isNotNull().usingRecursiveComparison().isEqualTo(responseDto);
    }

    @Test
    void getBeerPrices_test() {
        BeerResponseDto responseDto = AppChallengeUtilsForTest.createBeerResponseDto();
        Double price=10.5;

        //when
        when(beerService.getBeerPrice(anyLong(), any(), any())).thenReturn(price);
        when(beerMapper.toDto(any(Double.class))).thenReturn(responseDto);
        BeerResponseDto response = beerFacade.getBeerPrices(1L, "USD", 1);

        //then
        verify(beerService, times(1)).getBeerPrice(1L, "USD", 1);
        assertThat(response).isNotNull().usingRecursiveComparison().isEqualTo(responseDto);
    }





}
