package com.rleon.challenge.clever.beersApi.application.mapper;

import com.rleon.challenge.clever.beersApi.application.dto.request.BeerRequestDto;
import com.rleon.challenge.clever.beersApi.application.dto.response.BeerResponseDto;
import com.rleon.challenge.clever.beersApi.persistence.entity.Beer;
import org.springframework.stereotype.Component;

@Component
public class BeerMapper {

    public BeerResponseDto toDto(Beer beer) {
        return BeerResponseDto.builder()
                .id(beer.getId())
                .name(beer.getName())
                .brewery(beer.getBrewery())
                .country(beer.getCountry())
                .price(beer.getPrice())
                .currency(beer.getCurrency())
                .build();
    }

    public BeerResponseDto toDto(Double beerPrice) {
        return BeerResponseDto.builder()
                .totalPrice(beerPrice)
                .build();
    }

    public Beer toEntity(BeerRequestDto beerRequest) {
        return Beer.builder()
                .id(beerRequest.getId())
                .name(beerRequest.getName())
                .brewery(beerRequest.getBrewery())
                .country(beerRequest.getCountry())
                .price(beerRequest.getPrice())
                .currency(beerRequest.getCurrency())
                .build();
    }


}
