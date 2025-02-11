package org.nruharish.springmvc.controller;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nruharish.springmvc.model.Beer;
import org.nruharish.springmvc.services.BeerService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController

public class BeerController {
    private final BeerService beerService;
    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    @RequestMapping(method = RequestMethod.GET, value = BEER_PATH)
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }


    @RequestMapping(value = BEER_PATH_ID, method = RequestMethod.GET)
    public Beer getBeerById(@PathVariable("beerId") UUID beerId){
        return beerService.getBeerById(beerId);
    }

    @RequestMapping(value= BEER_PATH, method = RequestMethod.POST)
    public ResponseEntity handlePost(@RequestBody  Beer beer){
        Beer savedBeer = beerService.saveNewBeer(beer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/vi/beer/" + savedBeer.getId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping(BEER_PATH_ID)
    public ResponseEntity upddateById(@PathVariable  UUID beerId, @RequestBody Beer beer){

        beerService.updateBeerById(beerId, beer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity deleteBeer( @PathVariable  UUID beerId){

        beerService.deleteBeerById(beerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity upddateByPatch(@PathVariable  UUID beerId, @RequestBody Beer beer) {
        beerService.patchById(beerId, beer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFound(){
        return ResponseEntity.notFound().build();
    }

}
