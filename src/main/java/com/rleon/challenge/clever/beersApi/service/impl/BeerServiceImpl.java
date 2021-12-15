package com.rleon.challenge.clever.beersApi.service.impl;

import com.rleon.challenge.clever.beersApi.infrastructure.exception.custom.BeerNotFoundException;
import com.rleon.challenge.clever.beersApi.infrastructure.properties.ApplicationProperties;
import com.rleon.challenge.clever.beersApi.persistence.entity.Beer;
import com.rleon.challenge.clever.beersApi.persistence.repository.BeerRepository;
import com.rleon.challenge.clever.beersApi.provider.dto.CurrencyClientApi;
import com.rleon.challenge.clever.beersApi.provider.dto.response.CurrencyResponseDto;
import com.rleon.challenge.clever.beersApi.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerServiceImpl implements BeerService {

    private final ApplicationProperties properties;
    private final CurrencyClientApi clientApi;
    private final BeerRepository beerRepository;

    @Override
    public List<Beer> getAllBeers() {
        return beerRepository.findAll();
    }

    @Override
    public void createBeer(Beer beer) {
        beerRepository.save(beer);
    }

    @Override
    public Beer getBeerDetails(Long beerId) {
        return beerRepository.findById(beerId).orElseThrow(() -> new BeerNotFoundException(beerId));
    }

    @Override
    public Double getBeerPrice(Long beerId, String currency, Integer quantity) {
        Beer beer = getBeerDetails(beerId);
        CurrencyResponseDto responseDto = clientApi.getCurrencyValue(beer.getCurrency(), currency, properties.getKey());
        Float tcCurrency = responseDto.result.get(currency);
        double totalAmount = (beer.price * quantity) * tcCurrency;
        BigDecimal bd = BigDecimal.valueOf(totalAmount).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
