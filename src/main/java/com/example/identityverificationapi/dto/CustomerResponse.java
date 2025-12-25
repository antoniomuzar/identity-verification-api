package com.example.identityverificationapi.dto;

import com.example.identityverificationapi.model.KycStatus;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class CustomerResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String dateOfBirth;
    private String country;
    private Instant createdAt;
    private String kycStatus;
}
