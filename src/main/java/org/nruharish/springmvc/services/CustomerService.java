package org.nruharish.springmvc.services;

import org.nruharish.springmvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<Customer> listCustomers();

    Customer getCustomerById(UUID id);
}
