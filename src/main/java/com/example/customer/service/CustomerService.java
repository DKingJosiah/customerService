package com.example.customer.service;

import com.example.customer.dto.Request.CustomerRequest;
import com.example.customer.dto.Response.CustomerResponse;
import com.example.customer.entity.Address;
import com.example.customer.entity.Customer;
import com.example.customer.exception.CustomerNotFoundException;
import com.example.customer.mapper.CustomerMapper;
import com.example.customer.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = customerMapper.toEntity(customerRequest);
        customerRepository.save(customer);
        return customerMapper.toResponse(customer);
    }

    public CustomerResponse getCustomer(String externalId) {
        Customer customer = customerRepository.findByExternalId(externalId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        return customerMapper.toResponse(customer);
    }


    public CustomerResponse updateCustomer(String externalId, @Valid CustomerRequest customerRequest) {
        Customer customer = customerRepository.findByExternalId(externalId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());
        customer.setEmail(customerRequest.getEmail());
        customer.setFingerprint(customerRequest.getFingerprint());
        customer.setAddress(mapperToAddress(customerRequest));
        customerRepository.save(customer);
        return customerMapper.toResponse(customer);
    }

    private Address mapperToAddress(CustomerRequest request) {
        return Address.builder()
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .lga(request.getLga())
                .build();
    }

    public void deleteCustomer(String externalId) {
        Customer customer = customerRepository.findByExternalId(externalId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        customerRepository.delete(customer);
    }
}
