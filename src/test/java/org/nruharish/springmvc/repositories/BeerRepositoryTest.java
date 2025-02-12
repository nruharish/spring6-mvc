package org.nruharish.springmvc.repositories;

import org.junit.jupiter.api.Test;
import org.nruharish.springmvc.entities.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class BeerRepositoryTest {
    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeer(){
        Beer savedBeer = beerRepository.save(
                Beer.builder().beerName("My Beer").build()
        );
        assertThat(savedBeer).isNotNull();
        assertThat(savedBeer.getId()).isNotNull();
    }

}