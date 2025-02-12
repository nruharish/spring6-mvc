package org.nruharish.springmvc.services;

import org.nruharish.springmvc.model.CustomerDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<CustomerDTO> listCustomers();

    CustomerDTO getCustomerById(UUID id);

    CustomerDTO saveNewCustomer(CustomerDTO customer);

    void updateCustomerBy(UUID id, CustomerDTO customer);

    void deleteCustomerById(UUID id);

    void updateCustomerByPatch(UUID customerId, CustomerDTO customer);
}
