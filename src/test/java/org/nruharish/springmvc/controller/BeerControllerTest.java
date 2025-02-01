package org.nruharish.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.nruharish.springmvc.model.Beer;
import org.nruharish.springmvc.services.BeerService;
import org.nruharish.springmvc.services.BeerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
@WebMvcTest(BeerController.class)
class BeerControllerTest {

    //@Autowired
    //BeerController beerController;

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    BeerService beerService;
    BeerServiceImpl beerServiceImpl = new BeerServiceImpl();


    @Test
    void getBeerById() throws Exception {

        Beer testBeer = beerServiceImpl.listBeers().get(0);

        given(beerService.getBeerById(any(UUID.class))).willReturn(testBeer);

        //System.out.println(beerController.getBeerById(UUID.randomUUID()));
        System.out.println("++++++++++++++++++++In test getBeerById");
        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID()).
                accept(MediaType.APPLICATION_JSON))
                .andExpect((status().isOk()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }
}