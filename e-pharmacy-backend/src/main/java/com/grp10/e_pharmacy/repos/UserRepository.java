package com.grp10.e_pharmacy.repos;

import com.grp10.e_pharmacy.domain.Commande;
import com.grp10.e_pharmacy.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByPanier(Commande commande);

    boolean existsByPanierId(Long id);

}
