package com.grp10.e_pharmacy.service;

import com.grp10.e_pharmacy.domain.Commande;
import com.grp10.e_pharmacy.domain.Ordonance;
import com.grp10.e_pharmacy.domain.User;
import com.grp10.e_pharmacy.model.UserDTO;
import com.grp10.e_pharmacy.repos.CommandeRepository;
import com.grp10.e_pharmacy.repos.OrdonanceRepository;
import com.grp10.e_pharmacy.repos.UserRepository;
import com.grp10.e_pharmacy.util.NotFoundException;
import com.grp10.e_pharmacy.util.WebUtils;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final CommandeRepository commandeRepository;
    private final OrdonanceRepository ordonanceRepository;

    public UserService(final UserRepository userRepository,
            final CommandeRepository commandeRepository,
            final OrdonanceRepository ordonanceRepository) {
        this.userRepository = userRepository;
        this.commandeRepository = commandeRepository;
        this.ordonanceRepository = ordonanceRepository;
    }

    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("id"));
        return users.stream()
                .map(user -> mapToDTO(user, new UserDTO()))
                .toList();
    }

    public UserDTO get(final Long id) {
        return userRepository.findById(id)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getId();
    }

    public void update(final Long id, final UserDTO userDTO) {
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setNom(user.getNom());
        userDTO.setRole(user.getRole());
        userDTO.setPanier(user.getPanier() == null ? null : user.getPanier().getId());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setNom(userDTO.getNom());
        user.setRole(userDTO.getRole());
        final Commande panier = userDTO.getPanier() == null ? null : commandeRepository.findById(userDTO.getPanier())
                .orElseThrow(() -> new NotFoundException("panier not found"));
        user.setPanier(panier);
        return user;
    }

    public boolean panierExists(final Long id) {
        return userRepository.existsByPanierId(id);
    }

    public String getReferencedWarning(final Long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Ordonance doctorOrdonance = ordonanceRepository.findFirstByDoctor(user);
        if (doctorOrdonance != null) {
            return WebUtils.getMessage("user.ordonance.doctor.referenced", doctorOrdonance.getId());
        }
        final Ordonance patientOrdonance = ordonanceRepository.findFirstByPatient(user);
        if (patientOrdonance != null) {
            return WebUtils.getMessage("user.ordonance.patient.referenced", patientOrdonance.getId());
        }
        final Commande patientCommande = commandeRepository.findFirstByPatient(user);
        if (patientCommande != null) {
            return WebUtils.getMessage("user.commande.patient.referenced", patientCommande.getId());
        }
        final Commande livreurCommande = commandeRepository.findFirstByLivreur(user);
        if (livreurCommande != null) {
            return WebUtils.getMessage("user.commande.livreur.referenced", livreurCommande.getId());
        }
        return null;
    }

}
