package com.example.springmvc.controller;

import com.example.springmvc.model.Customer;
import com.example.springmvc.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity handlePost(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> listCustomers() {

        return customerService.listCustomers();
    }

    @RequestMapping(value = "{customerId}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable("customerId") UUID id) {

        return customerService.getCustomerById(id);
    }
}
