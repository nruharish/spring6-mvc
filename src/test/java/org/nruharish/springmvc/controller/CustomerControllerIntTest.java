package org.nruharish.springmvc.controller;

import org.junit.jupiter.api.Test;
import org.nruharish.springmvc.entities.Customer;
import org.nruharish.springmvc.model.CustomerDTO;
import org.nruharish.springmvc.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CustomerControllerIntTest {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerController customerController;

    @Test
    void testlistAllCustomers(){

        assertThat(customerController.listCustomers().size()).isEqualTo(3);

    }

    @Test
    void testGetCustomerById(){
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO customerDTO = customerController.getCustomerById(customer.getId());

        assertThat(customerDTO.getId()).isEqualTo(customer.getId());
    }

    @Test
    void testCustomerNotFoun() {
        assertThrows(NotFoundException.class, () -> {
            customerController.getCustomerById(UUID.randomUUID());
        });
    }

}