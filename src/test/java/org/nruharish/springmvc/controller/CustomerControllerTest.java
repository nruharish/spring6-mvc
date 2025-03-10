package org.nruharish.springmvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.nruharish.springmvc.model.CustomerDTO;
import org.nruharish.springmvc.services.CustomerService;
import org.nruharish.springmvc.services.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    CustomerService customerService;
    CustomerServiceImpl customerServiceImpl;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        customerServiceImpl = new CustomerServiceImpl();
    }

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor = ArgumentCaptor.forClass(UUID.class);

    @Captor
    ArgumentCaptor<CustomerDTO> customerArgumentCaptor = ArgumentCaptor.forClass(CustomerDTO.class);

    @Test
    void testUpdateCustomer() throws Exception {
        CustomerDTO customer = customerServiceImpl.listCustomers().get(0);

        mockMvc.perform(put("/api/v1/customer/" + customer.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)));
    }

    @Test
    void saveCustomer() throws Exception {
        CustomerDTO customer = customerServiceImpl.listCustomers().get(0);
        customer.setVersion(null);
        customer.setId(null);
        given(customerService.saveNewCustomer(customer)).willReturn(customerServiceImpl.listCustomers().get(1));

        mockMvc.perform(post("/api/v1/customer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));


    }

    @Test
    void deleteCustomerById() throws Exception {
        CustomerDTO customer = customerServiceImpl.listCustomers().get(0);

        mockMvc.perform(delete("/api/v1/customer/" + customer.getId() )
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        //ArgumentCaptor<UUID> uuidArgumentCaptor = ArgumentCaptor.forClass(UUID.class);
        verify(customerService).deleteCustomerById(uuidArgumentCaptor.capture());
        assertThat(customer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void patchCustomer() throws Exception {
        CustomerDTO customer = customerServiceImpl.listCustomers().get(0);

        Map<String, Object> customerMap =  new HashMap<>();
        customerMap.put("customerName", "New Name");

        mockMvc.perform(patch("/api/v1/customer/" + customer.getId() )
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerMap)))
                .andExpect(status().isNoContent());

        verify(customerService).updateCustomerByPatch(uuidArgumentCaptor.capture(), customerArgumentCaptor.capture());

        assertThat(customer.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        assertThat(customerMap.get("customerName")).isEqualTo(customerArgumentCaptor.getValue().getCustomerName());
    }

    @Test
    void listCustomers() throws Exception {
        given(customerService.listCustomers()).willReturn(customerServiceImpl.listCustomers());

        mockMvc.perform(get("/api/v1/customer")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect((status().isOk()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(3)));
    }

    @Test
    void getCustomerById() throws Exception {
        CustomerDTO customer = customerServiceImpl.listCustomers().get(0);
        given(customerService.getCustomerById(customer.getId())).willReturn(Optional.of(customer));

        mockMvc.perform(get("/api/v1/customer/" + customer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect((status().isOk()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(customer.getId().toString())))
                .andExpect(jsonPath("$.customerName", is(customer.getCustomerName())));
    }
}