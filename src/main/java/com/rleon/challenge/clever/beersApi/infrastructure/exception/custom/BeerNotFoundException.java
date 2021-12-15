package com.rleon.challenge.clever.beersApi.infrastructure.exception.custom;


import com.rleon.challenge.clever.beersApi.infrastructure.exception.BeerAbstractException;
import org.springframework.http.HttpStatus;

public class BeerNotFoundException extends BeerAbstractException {

    public BeerNotFoundException(final Long beerId) {

        this.setErrorCode(HttpStatus.NOT_FOUND.value());
        this.setErrorDescription("Beer with code: "+beerId + " Not found");
    }
}
