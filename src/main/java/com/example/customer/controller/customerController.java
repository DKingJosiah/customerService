package com.example.customer.controller;

import com.example.customer.dto.ApiResponse;
import com.example.customer.dto.Request;
import com.example.customer.dto.Response;
import com.example.customer.entity.Customer;
import com.example.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor

public class customerController {

    private final CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<ApiResponse<Response>> createCustomer(@Valid @RequestBody Request request) {
        Response data = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(data, "Customer created successfully"));
    }


       @GetMapping("/customer/{externalId}")
       public ResponseEntity<ApiResponse<Response>> getCustomer(@PathVariable String externalId) {
           return ResponseEntity.ok(ApiResponse.success(customerService.getCustomer(externalId), "Customer retrieved successfully"));

       }

       @PutMapping("/customer/{externalId}")
       public ResponseEntity<ApiResponse<Response>> updateCustomer(@PathVariable String externalId, @Valid @RequestBody Request request) {
           Response data = customerService.updateCustomer(externalId, request);
           return ResponseEntity.ok(ApiResponse.success(customerService.updateCustomer(externalId, request), "Customer updated successfully"));
       }

       @DeleteMapping("/customer/{externalId}")
       public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable String externalId) {
           customerService.deleteCustomer(externalId);
           return ResponseEntity.ok(ApiResponse.success(null, "Customer deleted successfully"));
       }
}


