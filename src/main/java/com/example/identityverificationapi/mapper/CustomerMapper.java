package com.example.identityverificationapi.mapper;

import com.example.identityverificationapi.dto.CustomerCreateRequest;
import com.example.identityverificationapi.dto.CustomerResponse;
import com.example.identityverificationapi.model.Customer;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerCreateRequest dto){
        return Customer.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .dateOfBirth(dto.getDateOfBirth())
                .country(dto.getCountry())
                .passwordHash(dto.getPassword())
                .createdAt(Instant.now())
                .build();
    }

    public CustomerResponse toResponse(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .dateOfBirth(customer.getDateOfBirth())
                .country(customer.getCountry())
                .createdAt(Instant.now())
                .build();
    }
}
