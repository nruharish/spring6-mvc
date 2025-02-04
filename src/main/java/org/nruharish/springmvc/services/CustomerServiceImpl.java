package org.nruharish.springmvc.services;

import org.nruharish.springmvc.model.Customer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService{
    private Map<UUID, Customer> customerMap;

    public CustomerServiceImpl() {
        this.customerMap = new HashMap<>();

        Customer customer1 = Customer.builder().customerName("Hakka").version(1).id(UUID.randomUUID()).createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();
        Customer customer2 = Customer.builder().customerName("Bukka").version(1).id(UUID.randomUUID()).createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();
        Customer customer3 = Customer.builder().customerName("Pulakeshi").version(1).id(UUID.randomUUID()).createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();
        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);
        customerMap.put(customer3.getId(), customer3);

    }
    @Override
    public List<Customer> listCustomers(){
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer getCustomerById(UUID id){
        return customerMap.get(id);
    }

    @Override
    public Customer saveNewCustomer(Customer customer){
        Customer savedCustomer = Customer.builder().customerName(customer.getCustomerName())
                .version(customer.getVersion())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                        .id(UUID.randomUUID())
                .build();
        customerMap.put(savedCustomer.getId(), savedCustomer);
        return savedCustomer;
    }

    @Override
    public void updateCustomerBy(UUID id, Customer customer){
        System.out.println("######Customerid" + id.toString());
        Customer savedCustomer = customerMap.get(id);
        savedCustomer.setCustomerName(customer.getCustomerName());
    }

    @Override
    public void deleteCustomerById(UUID id){
        customerMap.remove(id);
    }

    @Override
    public void updateCustomerByPatch(UUID customerId, Customer customer) {
        Customer savedCustomer = customerMap.get(customerId);

        if(StringUtils.hasText(customer.getCustomerName())){
            savedCustomer.setCustomerName(customer.getCustomerName());
        }
        customerMap.put(customerId, savedCustomer);
    }
}
