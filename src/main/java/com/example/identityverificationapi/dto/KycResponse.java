package com.example.identityverificationapi.dto;

import com.example.identityverificationapi.model.KycStatus;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class KycResponse {

    private Long id;
    private KycStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private String externalReferenceId;
}
