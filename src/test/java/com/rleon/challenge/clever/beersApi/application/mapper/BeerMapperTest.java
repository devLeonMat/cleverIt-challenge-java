package com.rleon.challenge.clever.beersApi.application.mapper;

import com.rleon.challenge.clever.beersApi.AppChallengeUtilsForTest;
import com.rleon.challenge.clever.beersApi.application.dto.request.BeerRequestDto;
import com.rleon.challenge.clever.beersApi.application.dto.response.BeerResponseDto;
import com.rleon.challenge.clever.beersApi.persistence.entity.Beer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class BeerMapperTest {

    private BeerMapper beerMapper;

    @BeforeEach
    void setUp() {
        beerMapper = new BeerMapper();
    }


    @Test
    void toDto_1() {
        Beer entity = AppChallengeUtilsForTest.createBeerEntity();
        BeerResponseDto expected = AppChallengeUtilsForTest.createBeerResponseDto();
        expected.setTotalPrice(null);

        BeerResponseDto result = beerMapper.toDto(entity);
        //then
        assertThat(result).isNotNull().usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void toDto_2() {
        Double value = 10.5;
        BeerResponseDto expected = AppChallengeUtilsForTest.createBeerResponseDto();

        BeerResponseDto result = beerMapper.toDto(value);
        //then
        assertThat(result.getTotalPrice()).isNotNull().usingRecursiveComparison().isEqualTo(expected.getTotalPrice());
    }



    @Test
    void toEntity_1() {
        Beer expected = AppChallengeUtilsForTest.createBeerEntity();
        BeerRequestDto requestDto = AppChallengeUtilsForTest.createBeerRequestDto();

        Beer result = beerMapper.toEntity(requestDto);
        //then
        assertThat(result).isNotNull().usingRecursiveComparison().isEqualTo(expected);
    }


}
