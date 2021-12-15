package com.rleon.challenge.clever.beersApi.service;

import com.rleon.challenge.clever.beersApi.persistence.entity.Beer;

import java.util.List;

public interface BeerService {

    List<Beer> getAllBeers();

    void createBeer(Beer beer);

    Beer getBeerDetails(Long beerId);

    Double getBeerPrice(Long beerId, String currency, Integer quantity);
}
