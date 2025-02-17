package org.nruharish.springmvc.mappers;

import org.mapstruct.Mapper;
import org.nruharish.springmvc.entities.Customer;
import org.nruharish.springmvc.model.CustomerDTO;


@Mapper
public interface CustomerMapper {
    CustomerDTO customertoCustomerDTO(Customer customer);
    Customer customerDTOToCustomer (CustomerDTO customerDTO);

}
