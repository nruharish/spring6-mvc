package org.nruharish.springmvc.controller;

import lombok.AllArgsConstructor;
import org.nruharish.springmvc.model.Customer;
import org.nruharish.springmvc.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

        Customer saveCustomer = customerService.saveNewCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/vi/customer/" + saveCustomer.getId().toString());
        return  new ResponseEntity(headers, HttpStatus.CREATED);

    }
    @PutMapping("{customerId}")
    public ResponseEntity updateCustomerById(@PathVariable  UUID customerId, @RequestBody  Customer customer){

        customerService.updateCustomerBy(customerId, customer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable  UUID id){
        customerService.deleteCustomerById(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("{id}")
    public ResponseEntity updateCustomerByPatch(@PathVariable  UUID id, @RequestBody  Customer customer) {
        customerService.updateCustomerByPatch(id, customer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
