package com.grp10.e_pharmacy.service;

import com.grp10.e_pharmacy.domain.Commande;
import com.grp10.e_pharmacy.domain.Medicament;
import com.grp10.e_pharmacy.domain.Ordonance;
import com.grp10.e_pharmacy.domain.Pharmacie;
import com.grp10.e_pharmacy.domain.User;
import com.grp10.e_pharmacy.model.CommandeDTO;
import com.grp10.e_pharmacy.repos.CommandeRepository;
import com.grp10.e_pharmacy.repos.MedicamentRepository;
import com.grp10.e_pharmacy.repos.OrdonanceRepository;
import com.grp10.e_pharmacy.repos.PharmacieRepository;
import com.grp10.e_pharmacy.repos.UserRepository;
import com.grp10.e_pharmacy.util.NotFoundException;
import com.grp10.e_pharmacy.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final PharmacieRepository pharmacieRepository;
    private final UserRepository userRepository;
    private final MedicamentRepository medicamentRepository;
    private final OrdonanceRepository ordonanceRepository;

    public CommandeService(final CommandeRepository commandeRepository,
            final PharmacieRepository pharmacieRepository, final UserRepository userRepository,
            final MedicamentRepository medicamentRepository,
            final OrdonanceRepository ordonanceRepository) {
        this.commandeRepository = commandeRepository;
        this.pharmacieRepository = pharmacieRepository;
        this.userRepository = userRepository;
        this.medicamentRepository = medicamentRepository;
        this.ordonanceRepository = ordonanceRepository;
    }

    public List<CommandeDTO> findAll() {
        final List<Commande> commandes = commandeRepository.findAll(Sort.by("id"));
        return commandes.stream()
                .map(commande -> mapToDTO(commande, new CommandeDTO()))
                .toList();
    }

    public CommandeDTO get(final Long id) {
        return commandeRepository.findById(id)
                .map(commande -> mapToDTO(commande, new CommandeDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final CommandeDTO commandeDTO) {
        final Commande commande = new Commande();
        mapToEntity(commandeDTO, commande);
        return commandeRepository.save(commande).getId();
    }

    public void update(final Long id, final CommandeDTO commandeDTO) {
        final Commande commande = commandeRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(commandeDTO, commande);
        commandeRepository.save(commande);
    }

    public void delete(final Long id) {
        commandeRepository.deleteById(id);
    }

    private CommandeDTO mapToDTO(final Commande commande, final CommandeDTO commandeDTO) {
        commandeDTO.setId(commande.getId());
        commandeDTO.setDate(commande.getDate());
        commandeDTO.setPrix(commande.getPrix());
        commandeDTO.setLivraison(commande.getLivraison());
        commandeDTO.setAddressLivraison(commande.getAddressLivraison());
        commandeDTO.setStatue(commande.getStatue());
        commandeDTO.setPharmacie(commande.getPharmacie() == null ? null : commande.getPharmacie().getId());
        commandeDTO.setPatient(commande.getPatient() == null ? null : commande.getPatient().getId());
        commandeDTO.setLivreur(commande.getLivreur() == null ? null : commande.getLivreur().getId());
        commandeDTO.setMedicaments(commande.getMedicaments().stream()
                .map(medicament -> medicament.getId())
                .toList());
        return commandeDTO;
    }

    private Commande mapToEntity(final CommandeDTO commandeDTO, final Commande commande) {
        commande.setDate(commandeDTO.getDate());
        commande.setPrix(commandeDTO.getPrix());
        commande.setLivraison(commandeDTO.getLivraison());
        commande.setAddressLivraison(commandeDTO.getAddressLivraison());
        commande.setStatue(commandeDTO.getStatue());
        final Pharmacie pharmacie = commandeDTO.getPharmacie() == null ? null : pharmacieRepository.findById(commandeDTO.getPharmacie())
                .orElseThrow(() -> new NotFoundException("pharmacie not found"));
        commande.setPharmacie(pharmacie);
        final User patient = commandeDTO.getPatient() == null ? null : userRepository.findById(commandeDTO.getPatient())
                .orElseThrow(() -> new NotFoundException("patient not found"));
        commande.setPatient(patient);
        final User livreur = commandeDTO.getLivreur() == null ? null : userRepository.findById(commandeDTO.getLivreur())
                .orElseThrow(() -> new NotFoundException("livreur not found"));
        commande.setLivreur(livreur);
        final List<Medicament> medicaments = medicamentRepository.findAllById(
                commandeDTO.getMedicaments() == null ? Collections.emptyList() : commandeDTO.getMedicaments());
        if (medicaments.size() != (commandeDTO.getMedicaments() == null ? 0 : commandeDTO.getMedicaments().size())) {
            throw new NotFoundException("one of medicaments not found");
        }
        commande.setMedicaments(medicaments.stream().collect(Collectors.toSet()));
        return commande;
    }

    public String getReferencedWarning(final Long id) {
        final Commande commande = commandeRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Ordonance commandeOrdonance = ordonanceRepository.findFirstByCommande(commande);
        if (commandeOrdonance != null) {
            return WebUtils.getMessage("commande.ordonance.commande.referenced", commandeOrdonance.getId());
        }
        final User panierUser = userRepository.findFirstByPanier(commande);
        if (panierUser != null) {
            return WebUtils.getMessage("commande.user.panier.referenced", panierUser.getId());
        }
        return null;
    }

}
