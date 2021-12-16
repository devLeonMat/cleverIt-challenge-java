package com.rleon.challenge.clever.beersApi;

import com.rleon.challenge.clever.beersApi.application.dto.request.BeerRequestDto;
import com.rleon.challenge.clever.beersApi.application.dto.response.BeerResponseDto;
import com.rleon.challenge.clever.beersApi.persistence.entity.Beer;
import com.rleon.challenge.clever.beersApi.provider.dto.response.CurrencyResponseDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppChallengeUtilsForTest {


    public static BeerResponseDto createBeerResponseDto() {
        return BeerResponseDto.builder()
                .id(1L)
                .name("Golden")
                .brewery("Kross")
                .country("Chile")
                .price(10.5)
                .currency("EUR")
                .totalPrice(10.5)
                .build();
    }

    public static BeerRequestDto createBeerRequestDto() {
        return BeerRequestDto.builder()
                .id(1L)
                .name("Golden")
                .brewery("Kross")
                .country("Chile")
                .price(10.5)
                .currency("EUR")
                .build();
    }

    public static Beer createBeerEntity() {
        return Beer.builder()
                .id(1L)
                .name("Golden")
                .brewery("Kross")
                .country("Chile")
                .price(10.5)
                .currency("EUR")
                .build();
    }

    public static CurrencyResponseDto createCurrencyResponseDto() {
        Map<String, Float> stringFloatMap = new HashMap<>();
        stringFloatMap.put("USD", 0.885f);
        return CurrencyResponseDto.builder()
                .base("USD")
                .result(stringFloatMap)
                .updated("2021-12-16 07:25:24")
                .ms(7)
                .build();
    }

    public static List<BeerResponseDto> createListBeersResponseDto(int quantity) {
        ArrayList<BeerResponseDto> beerResponseDtoArrayList = new ArrayList<>();
        int i = 0;
        do {
            ++i;
            beerResponseDtoArrayList.add(createBeerResponseDto());
        } while (i <= quantity);

        return beerResponseDtoArrayList;
    }

    public static List<Beer> createListBeers(int quantity) {
        ArrayList<Beer> beerArrayList = new ArrayList<>();
        int i = 0;
        do {
            ++i;
            beerArrayList.add(createBeerEntity());
        } while (i <= quantity);

        return beerArrayList;
    }

}




