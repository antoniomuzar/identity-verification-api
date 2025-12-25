package com.example.identityverificationapi.mapper;

import com.example.identityverificationapi.dto.KycResponse;
import com.example.identityverificationapi.model.KycRequest;
import org.springframework.stereotype.Component;

@Component
public class KycRequestMapper {


    public KycResponse toDto(KycRequest entity){
        return KycResponse.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .externalReferenceId(entity.getExternalReferenceId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
