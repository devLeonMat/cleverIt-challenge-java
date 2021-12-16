package com.rleon.challenge.clever.beersApi.service.impl;

import com.rleon.challenge.clever.beersApi.AppChallengeUtilsForTest;
import com.rleon.challenge.clever.beersApi.infrastructure.properties.ApplicationProperties;
import com.rleon.challenge.clever.beersApi.persistence.entity.Beer;
import com.rleon.challenge.clever.beersApi.persistence.repository.BeerRepository;
import com.rleon.challenge.clever.beersApi.provider.dto.CurrencyClientApi;
import com.rleon.challenge.clever.beersApi.provider.dto.response.CurrencyResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BeerServiceImplTest {

    @InjectMocks
    private BeerServiceImpl beerService;

    @Mock
    private CurrencyClientApi clientApi;

    @Mock
    private BeerRepository beerRepository;


    @Mock
    private ApplicationProperties applicationProperties;

    @BeforeEach
    void setUp() {
        beerService = new BeerServiceImpl(applicationProperties, clientApi, beerRepository);
    }

    @Test
    void getAllBeers_ok() {
        List<Beer> beerList = AppChallengeUtilsForTest.createListBeers(2);

        //when
        when(beerRepository.findAll()).thenReturn(beerList);
        List<Beer> beers = beerService.getAllBeers();

        //then
        assertThat(beerList).isEqualTo(beers);
        verify(beerRepository, times(1)).findAll();
    }

    @Test
    void createBeer_ok() {
        Beer beer = AppChallengeUtilsForTest.createBeerEntity();
        Beer responseEntity = AppChallengeUtilsForTest.createBeerEntity();

        //when
        given(beerRepository.save(any(Beer.class))).willReturn(responseEntity);
        beerService.createBeer(beer);

        //then
        verify(beerRepository, times(1)).save(beer);
    }

    @Test
    void getBeerDetails_ok() {
        Beer beer = AppChallengeUtilsForTest.createBeerEntity();
        Beer expected = AppChallengeUtilsForTest.createBeerEntity();

        //when
        given(beerRepository.findById(anyLong())).willReturn(Optional.ofNullable(beer));

        Beer response = beerService.getBeerDetails(1L);

        //then
        verify(beerRepository, times(1)).findById(1L);
        assertThat(response).isNotNull().usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getBeerPrice_ok() {
        Beer beer = AppChallengeUtilsForTest.createBeerEntity();
        Beer expected = AppChallengeUtilsForTest.createBeerEntity();
        Double value = 5.5;
        CurrencyResponseDto currencyResponseDto = AppChallengeUtilsForTest.createCurrencyResponseDto();

        //when
        when(applicationProperties.getKey()).thenReturn("8ec64ac619-0831749fa2");
        when(beerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(beer));
        when(clientApi.getCurrencyValue(any(), any(), any())).thenReturn(currencyResponseDto);

        Double response = beerService.getBeerPrice(1L, "USD", 1);

        //then
        assertThat(response).isNotNull();
    }
}
