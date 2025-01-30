package org.nruharish.springmvc.services;

import ch.qos.logback.core.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.nruharish.springmvc.model.Beer;
import org.nruharish.springmvc.model.BeerStyle;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    private Map<UUID, Beer> beerMap;
    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356222")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer3 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("12356")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);
    }

    @Override
    public List<Beer> listBeers(){
        return new ArrayList<>(beerMap.values());
    }
    @Override
    public Beer getBeerById(UUID id) {
        log.debug("Get Beer by id");
        return beerMap.get(id);
    }

    @Override
    public Beer saveNewBeer(Beer beer) {
        Beer savedBeer = Beer.builder().
        id(UUID.randomUUID())
                .beerName(beer.getBeerName())
                        .price(beer.getPrice())
                                .beerStyle(beer.getBeerStyle())
                                        .upc(beer.getUpc())
                                                .quantityOnHand(beer.getQuantityOnHand())
                                                        .createdDate(LocalDateTime.now())
                                                                .updateDate(LocalDateTime.now()).build();
        beerMap.put(savedBeer.getId(), savedBeer);
        return savedBeer;
    }

    @Override
    public void updateBeerById(UUID id, Beer beer) {
        Beer savedBeer = beerMap.get(id);
        savedBeer.setBeerName(beer.getBeerName());
        savedBeer.setPrice(beer.getPrice());
        savedBeer.setUpc(beer.getUpc());
        savedBeer.setQuantityOnHand(beer.getQuantityOnHand());

        beerMap.put(id, savedBeer);

    }

    @Override
    public void deleteBeerById(UUID id) {
        beerMap.remove(id);

    }

    @Override
    public void patchById(UUID id, Beer beer) {
        Beer savedBeer = beerMap.get(id);
        if(StringUtils.hasText(beer.getBeerName()))
            savedBeer.setBeerName(beer.getBeerName());
        if(beer.getPrice() != null)
            savedBeer.setPrice(beer.getPrice());
        if(beer.getUpc() != null)
            savedBeer.setUpc(beer.getUpc());
        if(beer.getQuantityOnHand() != null)
            savedBeer.setQuantityOnHand(beer.getQuantityOnHand());

        beerMap.put(id, savedBeer);
    }
}
