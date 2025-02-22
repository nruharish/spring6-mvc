package org.nruharish.springmvc.services;

import lombok.extern.slf4j.Slf4j;
import org.nruharish.springmvc.model.BeerDTO;
import org.nruharish.springmvc.model.BeerStyle;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    private Map<UUID, BeerDTO> beerMap;
    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();


        BeerDTO beer2 = BeerDTO.builder()
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
        BeerDTO beer1 = BeerDTO.builder()
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

        BeerDTO beer3 = BeerDTO.builder()
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
    public List<BeerDTO> listBeers(){
        return new ArrayList<>(beerMap.values());
    }
    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        log.debug("Get Beer by id");
        return Optional.ofNullable(beerMap.get(id));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beer) {
        BeerDTO savedBeer = BeerDTO.builder().
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
    public Optional<BeerDTO> updateBeerById(UUID id, BeerDTO beer) {
        BeerDTO savedBeer = beerMap.get(id);
        savedBeer.setBeerName(beer.getBeerName());
        savedBeer.setPrice(beer.getPrice());
        savedBeer.setUpc(beer.getUpc());
        savedBeer.setQuantityOnHand(beer.getQuantityOnHand());

        beerMap.put(id, savedBeer);
        return Optional.of(savedBeer);

    }

    @Override
    public Boolean deleteBeerById(UUID id) {
        beerMap.remove(id);
        return true;

    }

    @Override
    public Optional<BeerDTO> patchById(UUID id, BeerDTO beer) {
        BeerDTO savedBeer = beerMap.get(id);
        if(StringUtils.hasText(beer.getBeerName()))
            savedBeer.setBeerName(beer.getBeerName());
        if(beer.getPrice() != null)
            savedBeer.setPrice(beer.getPrice());
        if(beer.getUpc() != null)
            savedBeer.setUpc(beer.getUpc());
        if(beer.getQuantityOnHand() != null)
            savedBeer.setQuantityOnHand(beer.getQuantityOnHand());

        beerMap.put(id, savedBeer);
        return Optional.of(savedBeer);
    }
}
