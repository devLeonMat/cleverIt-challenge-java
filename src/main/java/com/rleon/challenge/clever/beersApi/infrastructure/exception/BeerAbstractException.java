package com.rleon.challenge.clever.beersApi.infrastructure.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BeerAbstractException extends RuntimeException{
    private String errorDescription;
    private int errorCode;
}
