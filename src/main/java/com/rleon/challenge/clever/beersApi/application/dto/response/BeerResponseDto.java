package com.rleon.challenge.clever.beersApi.application.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeerResponseDto {

    @JsonProperty(value = "Id")
    public Long id;

    @JsonProperty(value = "Name")
    public String name;

    @JsonProperty(value = "Brewery")
    public String brewery;

    @JsonProperty(value = "Country")
    public String country;

    @JsonProperty(value = "Price")
    public Double price;

    @JsonProperty(value = "Currency")
    public String currency;

    @JsonProperty(value = "Price Total")
    public Double totalPrice;


}
