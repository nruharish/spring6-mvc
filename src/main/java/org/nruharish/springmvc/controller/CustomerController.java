package org.nruharish.springmvc.controller;

import lombok.AllArgsConstructor;
import org.nruharish.springmvc.model.CustomerDTO;
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
    public List<CustomerDTO> listCustomers(){
        return customerService.listCustomers();
    }

    @RequestMapping("/{customerId}")
    public CustomerDTO getCustomerById(@PathVariable("customerId") UUID id){
        return customerService.getCustomerById(id).orElseThrow(NotFoundException::new);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity saveCutomer(@RequestBody CustomerDTO customer){

        CustomerDTO saveCustomer = customerService.saveNewCustomer(customer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/vi/customer/" + saveCustomer.getId().toString());
        return  new ResponseEntity(headers, HttpStatus.CREATED);

    }
    @PutMapping("{customerId}")
    public ResponseEntity updateCustomerById(@PathVariable  UUID customerId, @RequestBody CustomerDTO customer){

        if(customerService.updateCustomerBy(customerId, customer).isEmpty())
                throw new NotFoundException();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteById(@PathVariable  UUID id){
        customerService.deleteCustomerById(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("{id}")
    public ResponseEntity updateCustomerByPatch(@PathVariable  UUID id, @RequestBody CustomerDTO customer) {
        customerService.updateCustomerByPatch(id, customer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
