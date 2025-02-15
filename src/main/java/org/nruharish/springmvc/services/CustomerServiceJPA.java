package org.nruharish.springmvc.services;

import lombok.AllArgsConstructor;
import org.nruharish.springmvc.mappers.CustomerMapper;
import org.nruharish.springmvc.model.CustomerDTO;
import org.nruharish.springmvc.repositories.CustomerRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Primary
@AllArgsConstructor
public class CustomerServiceJPA implements  CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    @Override
    public List<CustomerDTO> listCustomers() {
        return List.of();
    }

    @Override
    public CustomerDTO getCustomerById(UUID id) {
        return null;
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer) {
        return null;
    }

    @Override
    public void updateCustomerBy(UUID id, CustomerDTO customer) {

    }

    @Override
    public void deleteCustomerById(UUID id) {

    }

    @Override
    public void updateCustomerByPatch(UUID customerId, CustomerDTO customer) {

    }
}
