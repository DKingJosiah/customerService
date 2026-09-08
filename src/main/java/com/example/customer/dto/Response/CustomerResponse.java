package com.example.customer.dto.Response;


import com.example.customer.entity.Address;
import com.example.customer.entity.KycStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {


    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private Address address;

    private String externalId;

    private Long version;

    private KycStatus kycStatus;

    private boolean isActive;

    private String fingerprint;

    private Instant createdAt;

    private Instant updatedAt;

}
