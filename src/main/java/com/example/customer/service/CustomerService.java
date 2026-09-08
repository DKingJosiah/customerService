package com.example.customer.service;

import com.example.customer.dto.Response;
import com.example.customer.entity.Customer;
import com.example.customer.exception.CustomerNotFoundException;
import com.example.customer.mapper.CustomerMapper;
import com.example.customer.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.customer.dto.Request;


@Service
@RequiredArgsConstructor

public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public Response createCustomer(Request request) {
        Customer customer = customerMapper.toEntity(request);
        customerRepository.save(customer);
        return customerMapper.toResponse(customer);
    }

    public Response getCustomer(String externalId) {
        Customer customer = customerRepository.findByExternalId(externalId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return customerMapper.toResponse(customer);
    }


    public Response updateCustomer(String externalId, @Valid Request request) {
        Customer customer = customerRepository.findByExternalId(externalId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        customerRepository.save(customer);
        return customerMapper.toResponse(customer);
    }

    public void deleteCustomer(String externalId) {
        Customer customer = customerRepository.findByExternalId(externalId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        customerRepository.delete(customer);
    }
}
