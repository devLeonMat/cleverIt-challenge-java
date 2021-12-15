package com.rleon.challenge.clever.beersApi.application.controller;


import com.rleon.challenge.clever.beersApi.application.dto.request.BeerRequestDto;
import com.rleon.challenge.clever.beersApi.application.dto.response.BeerResponseDto;
import com.rleon.challenge.clever.beersApi.application.facade.BeerFacade;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/beers")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerController {

    private final BeerFacade beerFacade;

    @GetMapping
    public ResponseEntity<List<BeerResponseDto>> getAllBeers() {
        log.info("[BeerController:getAllBeers]");
        return ResponseEntity.ok().body(beerFacade.findAllBeers());
    }

    @PostMapping
    public ResponseEntity<BeerResponseDto> createBeers(@Valid @RequestBody BeerRequestDto beerRequest) {
        log.info("[BeerController:createBeers]");
        beerFacade.createBeer(beerRequest);
        return ResponseEntity.created(URI.create("/beers")).build();
    }

    @GetMapping("/{beerID}")
    public ResponseEntity<BeerResponseDto> getDetails(@PathVariable(value = "beerID") Long beerID) {
        log.info("[BeerController:getDetails]");
        return ResponseEntity.ok().body(beerFacade.getDetailsById(beerID));
    }

    @GetMapping("/{beerID}/boxprice")
    public ResponseEntity<BeerResponseDto> getDetails(@PathVariable(value = "beerID") Long beerID, @RequestParam("currency") String currency, @RequestParam("quantity") Integer quantity) {
        log.info("[BeerController:getDetails]");
        return ResponseEntity.ok().body(beerFacade.getBeerPrices(beerID, currency, quantity));
    }


}
