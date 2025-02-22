package org.nruharish.springmvc.services;

import lombok.RequiredArgsConstructor;
import org.nruharish.springmvc.entities.Beer;
import org.nruharish.springmvc.mappers.BeerMapper;
import org.nruharish.springmvc.model.BeerDTO;
import org.nruharish.springmvc.repositories.BeerRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
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
        Optional<Beer> beer = beerRepository.findById(id);
        return Optional.ofNullable(beerMapper.beerToBeerDTO(beer.orElse(null)));
        //return null;
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beerDTO) {
        Beer beer = beerMapper.beerDtoToBeer(beerDTO);
        Beer savedBeer = beerRepository.save(beer);
        return beerMapper.beerToBeerDTO(savedBeer);
    }

    @Override
    public Optional<BeerDTO> updateBeerById(UUID id, BeerDTO beer) {

        AtomicReference<Optional<BeerDTO>> automicReference = new AtomicReference<>();
        System.out.println("I was here@@@@@@@@@@@@@@@@@@");
        beerRepository.findById(id).ifPresentOrElse(
                foundBeer -> {
                    foundBeer.setBeerName(beer.getBeerName());
                    foundBeer.setBeerStyle(beer.getBeerStyle());
                    foundBeer.setUpc(beer.getUpc());
                    foundBeer.setPrice(beer.getPrice());
                    System.out.println("I was here@@@@@@@@@@@@@@@@@@");
                    automicReference.set(Optional.of(beerMapper.beerToBeerDTO(beerRepository.save(foundBeer)
                    )));


                }, () -> {
                    automicReference.set(Optional.empty());
                });
        System.out.println("I was here@@@@@@@@@@@@@@@@@@");
                return automicReference.get();
    }

    @Override
    public Boolean deleteBeerById(UUID id) {
        if(beerRepository.existsById(id)) {
            beerRepository.deleteById(id);
            return true;
        }
        return false;

    }

    @Override
    public void patchById(UUID id, BeerDTO beer) {

    }
}
