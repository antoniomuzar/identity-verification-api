package com.example.identityverificationapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    private String dateOfBirth;

    private String country;

    @Column(nullable = false)
    private String passwordHash;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private KycRequest kycRequest;

    private Instant createdAt;




}
