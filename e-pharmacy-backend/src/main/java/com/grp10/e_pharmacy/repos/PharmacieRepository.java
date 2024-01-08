package com.grp10.e_pharmacy.repos;

import com.grp10.e_pharmacy.domain.Pharmacie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PharmacieRepository extends JpaRepository<Pharmacie, Long> {
}
