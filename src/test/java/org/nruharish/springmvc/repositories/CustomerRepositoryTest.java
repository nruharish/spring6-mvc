package org.nruharish.springmvc.repositories;

import org.junit.jupiter.api.Test;
import org.nruharish.springmvc.entities.Beer;
import org.nruharish.springmvc.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;
    @Test
    void saveCustomer(){
        Customer savedCustomer = customerRepository.save(Customer.builder().customerName("TestCust").build());

        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getId()).isNotNull();
    }

}