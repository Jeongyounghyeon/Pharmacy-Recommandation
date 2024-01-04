package com.example.PharmacyRecommendation.pharmacy.repository;

import com.example.PharmacyRecommendation.pharmacy.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
}
