package com.rleon.challenge.clever.beersApi.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BeerRequestDto {

    @JsonProperty(value = "Id")
    public Long id;

    @JsonProperty(value = "Name")
    @NotBlank
    @NotNull(message = "name is required")
    public String name;

    @JsonProperty(value = "Brewery")
    @NotBlank
    @NotNull(message = "brewery is required")
    public String brewery;

    @JsonProperty(value = "Country")
    @NotBlank
    @NotNull(message = "country is required")
    public String country;

    @JsonProperty(value = "Price")
    @NotNull
    public Double price;

    @JsonProperty(value = "Currency")
    @NotBlank
    @NotNull(message = "currency is required")
    public String currency;

}
