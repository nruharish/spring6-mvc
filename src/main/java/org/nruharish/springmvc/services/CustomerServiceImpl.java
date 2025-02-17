package org.nruharish.springmvc.services;

import org.nruharish.springmvc.model.CustomerDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService{
    private Map<UUID, CustomerDTO> customerMap;

    public CustomerServiceImpl() {
        this.customerMap = new HashMap<>();

        CustomerDTO customer1 = CustomerDTO.builder().customerName("Hakka").version(1).id(UUID.randomUUID()).createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();
        CustomerDTO customer2 = CustomerDTO.builder().customerName("Bukka").version(1).id(UUID.randomUUID()).createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();
        CustomerDTO customer3 = CustomerDTO.builder().customerName("Pulakeshi").version(1).id(UUID.randomUUID()).createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();
        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);

    }
    @Override
    public List<CustomerDTO> listCustomers(){
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id){
        return Optional.ofNullable(customerMap.get(id));
    }

    @Override
    public CustomerDTO saveNewCustomer(CustomerDTO customer){
        CustomerDTO savedCustomer = CustomerDTO.builder().customerName(customer.getCustomerName())
                .version(customer.getVersion())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                        .id(UUID.randomUUID())
                .build();
        customerMap.put(savedCustomer.getId(), savedCustomer);
        return savedCustomer;
    }

    @Override
    public void updateCustomerBy(UUID id, CustomerDTO customer){
        System.out.println("######Customerid" + id.toString());
        CustomerDTO savedCustomer = customerMap.get(id);
        savedCustomer.setCustomerName(customer.getCustomerName());
    }

    @Override
    public void deleteCustomerById(UUID id){
        customerMap.remove(id);
    }

    @Override
    public void updateCustomerByPatch(UUID customerId, CustomerDTO customer) {
        CustomerDTO savedCustomer = customerMap.get(customerId);

        if(StringUtils.hasText(customer.getCustomerName())){
            savedCustomer.setCustomerName(customer.getCustomerName());
        }
        customerMap.put(customerId, savedCustomer);
    }
}
