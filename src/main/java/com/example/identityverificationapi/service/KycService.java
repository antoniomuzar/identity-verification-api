package com.example.identityverificationapi.service;

import com.example.identityverificationapi.exception.BadRequestException;
import com.example.identityverificationapi.exception.NotFoundException;
import com.example.identityverificationapi.model.Customer;
import com.example.identityverificationapi.model.KycRequest;
import com.example.identityverificationapi.model.KycStatus;
import com.example.identityverificationapi.repository.CustomerRepository;
import com.example.identityverificationapi.repository.KycRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KycService {

    private final KycRepository kycRepository;
    private final CustomerRepository customerRepository;

    public KycRequest startKyc(Long customerId){

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new NotFoundException("Customer not found with id: " + customerId));

        KycRequest kyc = KycRequest.builder()
                .customer(customer)
                .status(KycStatus.PENDING)
                .externalReferenceId(UUID.randomUUID().toString())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
        customer.setKycRequest(kyc);
        return kycRepository.save(kyc);
    }

    public KycRequest getByCustomerId(Long customerId){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new RuntimeException("Customer not found"));

        if(customer.getKycRequest() == null){
            throw new BadRequestException("Kyc not started");
        }
        return customer.getKycRequest();
    }
}
