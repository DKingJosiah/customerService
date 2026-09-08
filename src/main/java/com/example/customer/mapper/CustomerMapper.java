package com.example.customer.mapper;

import com.example.customer.dto.Request.CustomerRequest;
import com.example.customer.dto.Response.CustomerResponse;
import com.example.customer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "externalId", ignore = true)
    @Mapping(target = "kycStatus", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "address.address", source = "address")
    @Mapping(target = "address.city", source = "city")
    @Mapping(target = "address.state", source = "state")
    @Mapping(target = "address.lga", source = "lga")
    Customer toEntity(CustomerRequest customerRequest);

    CustomerResponse toResponse(Customer customer);

}
