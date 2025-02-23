package org.nruharish.springmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nruharish.springmvc.entities.Beer;
import org.nruharish.springmvc.mappers.BeerMapper;
import org.nruharish.springmvc.model.BeerDTO;
import org.nruharish.springmvc.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BeerControllerIT {
    @Autowired
    BeerController beerController;
    @Autowired
    BeerRepository beerRepository;
    @Autowired
    BeerMapper beerMapper;
    @Autowired
    WebApplicationContext wac;
    @Autowired
    ObjectMapper objectMapper;

    MockMvc mockMvc;

    @BeforeEach
    void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    void testPatchBeerBadName() throws Exception {
        Beer beer = beerRepository.findAll().get(0);

        Map<String, Object> beerMap = new HashMap<>();
        beerMap.put("beerName", "New Name 1111111111111111111111111111ffffffff111111111111111111");

        mockMvc.perform(patch("/api/v1/beer/" + beer.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerMap)))
                .andExpect(status().isBadRequest());

    }



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

    @Transactional
    @Rollback
    @Test
    void saveNewBeerTest(){
        BeerDTO beerDTO = BeerDTO.builder()
                .beerName("New Beer").build();
        ResponseEntity responseEntity = beerController.handlePost(beerDTO);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(201));
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String [] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");

        UUID uuid= UUID.fromString(locationUUID[4]);
        Beer beer = beerRepository.findById(uuid).get();

        assertThat(beer).isNotNull();
    }

    @Transactional
    @Rollback
    @Test
    void updateExistingBeer(){
        Beer beer = beerRepository.findAll().get(0);
        BeerDTO beerDTO = beerMapper.beerToBeerDTO(beer);
        beerDTO.setId(null);
        beerDTO.setVersion(null);
        final String updatedName = "UPDATED";
        beerDTO.setBeerName(updatedName);
        ResponseEntity responseEntity = beerController.upddateById(beer.getId(), beerDTO);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.valueOf(204));
        Beer updatedBeer = beerRepository.findById(beer.getId()).get();
        assertThat(updatedBeer.getBeerName()).isEqualTo(updatedName);
    }

    @Test
    @Rollback
    @Transactional
    void testUpdateNotFound (){
        assertThrows(NotFoundException.class , ()->{
            beerController.upddateById(UUID.randomUUID(), BeerDTO.builder().build());
        });
    }

    @Test
    @Rollback
    @Transactional
    void deletebyId(){
        Beer beer = beerRepository.findAll().get(0);
        ResponseEntity responseEntity = beerController.deleteBeer(beer.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        assertThat(beerRepository.findById(beer.getId())).isEmpty();
    }

    @Test
    @Rollback
    @Transactional
    void deleteByIdNotFound(){

        assertThrows( NotFoundException.class, ()->{
            beerController.deleteBeer(UUID.randomUUID());
        });

    }

}