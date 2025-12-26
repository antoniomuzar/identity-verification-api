package com.example.identityverificationapi.controller;

import com.example.identityverificationapi.dto.CustomerCreateRequest;
import com.example.identityverificationapi.dto.CustomerResponse;
import com.example.identityverificationapi.mapper.CustomerMapper;
import com.example.identityverificationapi.model.Customer;
import com.example.identityverificationapi.service.CustomerService;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
            @RequestBody @Valid CustomerCreateRequest request){

        Customer customer = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerMapper.toResponse(customer));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable Long id){
        Customer customer = customerService.getById(id);
        return ResponseEntity.ok(customerMapper.toResponse(customer));
    }
}
