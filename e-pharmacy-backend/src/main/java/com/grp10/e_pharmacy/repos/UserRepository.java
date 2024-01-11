package com.grp10.e_pharmacy.repos;

import com.grp10.e_pharmacy.domain.Commande;
import com.grp10.e_pharmacy.domain.User;
import com.grp10.e_pharmacy.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByPanier(Commande commande);
    List<User> findByRole(Role role);
    boolean existsByPanierId(Long id);

}
