package org.nruharish.springmvc.services;

import lombok.AllArgsConstructor;
import org.nruharish.springmvc.entities.Customer;
import org.nruharish.springmvc.mappers.CustomerMapper;
import org.nruharish.springmvc.model.CustomerDTO;
import org.nruharish.springmvc.repositories.CustomerRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@AllArgsConstructor
public class CustomerServiceJPA implements  CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    @Override
    public List<CustomerDTO> listCustomers() {
        return customerRepository.findAll().stream().
                map(customerMapper::customertoCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        Optional<Customer> customer =  customerRepository.findById(id);
        return Optional.ofNullable(customerMapper.customertoCustomerDTO(customer.orElse(null)));
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO customerDTOSaved = customerMapper.customertoCustomerDTO(savedCustomer);
        return customerDTOSaved;
    }

    @Override
    public Optional<CustomerDTO> updateCustomerBy(UUID id, CustomerDTO customer) {
        Optional<Customer> foundCustomerOpt = customerRepository.findById(id);
        if(foundCustomerOpt.isEmpty())
            return Optional.empty();
        Customer foundCustomer = foundCustomerOpt.get();

        foundCustomer.setCustomerName(customer.getCustomerName());
        Customer savedCustomer = customerRepository.save(foundCustomer);
        return Optional.of(customerMapper.customertoCustomerDTO(savedCustomer));

    }

    @Override
    public void deleteCustomerById(UUID id) {

    }

    @Override
    public void updateCustomerByPatch(UUID customerId, CustomerDTO customer) {

    }
}
