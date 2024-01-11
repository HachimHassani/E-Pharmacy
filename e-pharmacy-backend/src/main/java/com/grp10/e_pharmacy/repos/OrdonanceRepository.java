package com.grp10.e_pharmacy.repos;

import com.grp10.e_pharmacy.domain.Commande;
import com.grp10.e_pharmacy.domain.Medicament;
import com.grp10.e_pharmacy.domain.Ordonance;
import com.grp10.e_pharmacy.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrdonanceRepository extends JpaRepository<Ordonance, Long> {

    Ordonance findFirstByDoctor(User user);

    Ordonance findFirstByPatient(User user);

    Ordonance findFirstByCommande(Commande commande);

    List<Ordonance> findAllByMedicaments(Medicament medicament);

}
