package org.nruharish.springmvc.bootstrap;

import lombok.RequiredArgsConstructor;
import org.nruharish.springmvc.entities.Beer;
import org.nruharish.springmvc.entities.Customer;
import org.nruharish.springmvc.model.BeerStyle;
import org.nruharish.springmvc.repositories.BeerRepository;
import org.nruharish.springmvc.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final BeerRepository beerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBeerData();
        loadCustomerData();

    }

    private void loadCustomerData() {
        if(customerRepository.count() > 0)
            return;
        Customer customer1 = Customer.builder().customerName("Hakka").createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();
        Customer customer2 = Customer.builder().customerName("Bukka").createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();
        Customer customer3 = Customer.builder().customerName("Pulakeshi").createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
    }

    private void loadBeerData() {
        if(beerRepository.count() > 0)
            return;
        Beer beer2 = Beer.builder()
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356222")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        Beer beer1 = Beer.builder()
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        Beer beer3 = Beer.builder()
                .beerName("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("12356")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        beerRepository.save(beer1);
        beerRepository.save(beer2);
        beerRepository.save(beer3);


    }
}


