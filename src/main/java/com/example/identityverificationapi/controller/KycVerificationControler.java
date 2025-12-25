package com.example.identityverificationapi.controller;

import com.example.identityverificationapi.dto.KycResponse;
import com.example.identityverificationapi.mapper.KycRequestMapper;
import com.example.identityverificationapi.model.KycRequest;
import com.example.identityverificationapi.service.KycService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/kyc")
@RequiredArgsConstructor
public class KycVerificationControler {

    private final KycService kycService;

    private final KycRequestMapper kycRequestMapper;

    @PostMapping("/{customerId}")
    public ResponseEntity<KycResponse> startKyc(@PathVariable Long customerId){
        KycRequest kyc= kycService.startKyc(customerId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(kycRequestMapper.toDto(kyc));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<KycResponse> getStatus(@PathVariable Long customerId){
        KycRequest kyc = kycService.getByCustomerId(customerId);
        return ResponseEntity.ok(kycRequestMapper.toDto(kyc));
    }
}
