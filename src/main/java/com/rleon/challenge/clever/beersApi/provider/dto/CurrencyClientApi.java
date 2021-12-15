package com.rleon.challenge.clever.beersApi.provider.dto;

import com.rleon.challenge.clever.beersApi.provider.dto.response.CurrencyResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "currency-client", url = "${client.currency.api}")
@Repository
public interface CurrencyClientApi {

    @GetMapping("/fetch-one")
    CurrencyResponseDto getCurrencyValue(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("api_key") String apiKey);

}
