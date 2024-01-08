package com.grp10.e_pharmacy.repos;

import com.grp10.e_pharmacy.domain.Commande;
import com.grp10.e_pharmacy.domain.Ordonance;
import com.grp10.e_pharmacy.domain.Pharmacie;
import com.grp10.e_pharmacy.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommandeRepository extends JpaRepository<Commande, Long> {

    Commande findFirstByPharmacie(Pharmacie pharmacie);

    Commande findFirstByOrdonance(Ordonance ordonance);

    Commande findFirstByPatient(User user);

    Commande findFirstByLivreur(User user);

    boolean existsByOrdonanceId(Long id);

}
