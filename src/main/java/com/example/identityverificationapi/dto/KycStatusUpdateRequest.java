package com.example.identityverificationapi.dto;

import com.example.identityverificationapi.model.KycStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class KycStatusUpdateRequest {

    @NotNull
    private KycStatus status;
}
