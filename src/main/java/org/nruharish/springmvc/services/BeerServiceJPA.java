package org.nruharish.springmvc.services;

import lombok.RequiredArgsConstructor;
import org.nruharish.springmvc.mappers.BeerMapper;
import org.nruharish.springmvc.model.BeerDTO;
import org.nruharish.springmvc.repositories.BeerRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class BeerServiceJPA implements BeerService{
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public List<BeerDTO> listBeers() {
        return beerRepository.findAll()
                .stream()
                .map(beerMapper::beerToBeerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
       // BeerDTO beerDTO = beerRepository.findById(id);
        //return beerMapper.beerToBeerDTO();
        return null;
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {
        return null;
    }

    @Override
    public void updateBeerById(UUID id, BeerDTO beer) {

    }

    @Override
    public void deleteBeerById(UUID id) {

    }

    @Override
    public void patchById(UUID id, BeerDTO beer) {

    }
}
