package com.example.identityverificationapi.service;

import com.example.identityverificationapi.dto.CustomerCreateRequest;
import com.example.identityverificationapi.exception.NotFoundException;
import com.example.identityverificationapi.mapper.CustomerMapper;
import com.example.identityverificationapi.model.Customer;
import com.example.identityverificationapi.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public Customer createCustomer(CustomerCreateRequest request){

        Customer customer = customerMapper.toEntity(request);
        return customerRepository.save(customer);
    }

    public Customer getById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Customer not found with id: " + id));
        }
    }
