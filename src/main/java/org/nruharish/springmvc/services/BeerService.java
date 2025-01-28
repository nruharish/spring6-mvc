package org.nruharish.springmvc.services;

import org.nruharish.springmvc.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService {
    List<Beer> listBeers();

    Beer getBeerById(UUID id);
}
