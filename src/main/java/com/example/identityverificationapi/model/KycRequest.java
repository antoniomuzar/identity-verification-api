package com.example.identityverificationapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "kyc_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KycRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private KycStatus status;

    private String externalReferenceId;

    private Instant createdAt;

    private Instant updatedAt;

}
