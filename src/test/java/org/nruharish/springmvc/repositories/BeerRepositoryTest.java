package org.nruharish.springmvc.repositories;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.nruharish.springmvc.entities.Beer;
import org.nruharish.springmvc.model.BeerStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class BeerRepositoryTest {
    @Autowired
    BeerRepository beerRepository;


    @Test
    void testSaveBeer(){
        Beer savedBeer = beerRepository.save(
                Beer.builder().beerName("My Beer")
                                .beerStyle(BeerStyle.PORTER)
                                        .upc("321")
                                                .price(new BigDecimal("11.34")).build()
        );
        beerRepository.flush();
        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }
    @Test
    void testSaveBeerVEryLongName(){

        assertThrows( ConstraintViolationException.class, () ->{

            Beer savedBeer = beerRepository.save(
                    Beer.builder().beerName("My Beer11111111111111111111111111111111111111111111111111111111111111111")
                            .beerStyle(BeerStyle.PORTER)
                            .upc("321")
                            .price(new BigDecimal("11.34")).build()
            );
            beerRepository.flush();
        });

    }
}