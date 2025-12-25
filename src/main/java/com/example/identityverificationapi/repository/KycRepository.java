package com.example.identityverificationapi.repository;

import com.example.identityverificationapi.model.KycRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KycRepository extends JpaRepository<KycRequest, Long> {
}
