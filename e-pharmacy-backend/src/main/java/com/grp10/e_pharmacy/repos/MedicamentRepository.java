package com.grp10.e_pharmacy.repos;

import com.grp10.e_pharmacy.domain.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MedicamentRepository extends JpaRepository<Medicament, Long> {

    boolean existsByNomIgnoreCase(String nom);

}
