package com.example.customer.controller;

import com.example.customer.dto.Response.ApiResponse;
import com.example.customer.dto.Request.CustomerRequest;
import com.example.customer.dto.Response.CustomerResponse;
import com.example.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor

public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
        CustomerResponse data = customerService.createCustomer(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(data, "Customer created successfully"));
    }


       @GetMapping("/customer/{externalId}")
       public ResponseEntity<ApiResponse<CustomerResponse>> getCustomer(@PathVariable String externalId) {
           return ResponseEntity.ok(ApiResponse.success(customerService.getCustomer(externalId), "Customer retrieved successfully"));

       }

@PutMapping("/customer/{externalId}")
        public ResponseEntity<ApiResponse<CustomerResponse>> updateCustomer(@PathVariable String externalId, @Valid @RequestBody CustomerRequest customerRequest) {
            CustomerResponse data = customerService.updateCustomer(externalId, customerRequest);
            return ResponseEntity.ok(ApiResponse.success(data, "Customer updated successfully"));
        }

       @DeleteMapping("/customer/{externalId}")
       public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable String externalId) {
           customerService.deleteCustomer(externalId);
           return ResponseEntity.ok(ApiResponse.success(null, "Customer deleted successfully"));
       }
}


