package com.rleon.challenge.clever.beersApi.application.facade;

import com.rleon.challenge.clever.beersApi.application.dto.request.BeerRequestDto;
import com.rleon.challenge.clever.beersApi.application.dto.response.BeerResponseDto;
import com.rleon.challenge.clever.beersApi.application.mapper.BeerMapper;
import com.rleon.challenge.clever.beersApi.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerFacade {

    private final BeerService beerService;

    private final BeerMapper beerMapper;

    public List<BeerResponseDto> findAllBeers() {
        return beerService.getAllBeers()
                .stream()
                .map(beerMapper::toDto)
                .collect(Collectors.toList());
    }

    public void createBeer(BeerRequestDto beerRequest) {
        beerService.createBeer(beerMapper.toEntity(beerRequest));
    }

    public BeerResponseDto getDetailsById(Long beerId){
        return beerMapper.toDto(beerService.getBeerDetails(beerId));
    }

    public BeerResponseDto getBeerPrices(Long beerId, String currency, Integer quantity){
        return beerMapper.toDto(beerService.getBeerPrice(beerId, currency, quantity));
    }

}
