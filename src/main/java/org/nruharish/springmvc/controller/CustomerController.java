package org.nruharish.springmvc.controller;

import lombok.AllArgsConstructor;
import org.nruharish.springmvc.model.Customer;
import org.nruharish.springmvc.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity saveCutomer(@RequestBody Customer customer){

        customerService.saveNewCustomer(customer);
        return  new ResponseEntity(HttpStatus.CREATED);


    }

}
