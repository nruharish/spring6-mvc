package org.nruharish.springmvc.controller;

import lombok.AllArgsConstructor;
import org.nruharish.springmvc.model.Customer;
import org.nruharish.springmvc.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class CustomerController {
    @Autowired
    public final CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> listCustomers(){
        return customerService.listCustomers();
    }

    @RequestMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable("customerId") UUID id){
        return customerService.getCustomerById(id);
    }

}
