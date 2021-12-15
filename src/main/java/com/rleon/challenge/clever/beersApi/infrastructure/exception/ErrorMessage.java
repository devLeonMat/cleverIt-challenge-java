package com.rleon.challenge.clever.beersApi.infrastructure.exception;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private int errorCode;
    private String errorDescription;


}
