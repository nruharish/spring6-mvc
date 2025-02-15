package org.nruharish.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.nruharish.springmvc.entities.Beer;
import org.nruharish.springmvc.model.BeerDTO;
import org.nruharish.springmvc.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class BeerControllerIT {
    @Autowired
    BeerController beerController;
    @Autowired
    BeerRepository beerRepository;


    @Test
    void testBeerIdNotFound(){
        assertThrows(NotFoundException.class, ()->{
                beerController.getBeerById(UUID.randomUUID());
        });
    }
    @Test
    void testGetById(){
        Beer beer = beerRepository.findAll().get(0);
        assertThat(beer).isNotNull();
        System.out.println(beer.getId());
        BeerDTO beerDTO = beerController.getBeerById(beer.getId());

        assertThat(beerDTO).isNotNull();


    }
    @Test
    void testListBeers(){
        List<BeerDTO> dtos = beerController.listBeers();

        assertThat(dtos.size()).isEqualTo(3);
    }

    @Rollback
    @Transactional
    @Test
    void testEmptyList(){
        beerRepository.deleteAll();;
        List<BeerDTO> dtos = beerController.listBeers();
        assertThat(dtos.size()).isEqualTo(0);
    }


}