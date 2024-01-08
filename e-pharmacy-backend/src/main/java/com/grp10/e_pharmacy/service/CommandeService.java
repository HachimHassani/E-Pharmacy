package com.grp10.e_pharmacy.service;

import com.grp10.e_pharmacy.domain.Commande;
import com.grp10.e_pharmacy.domain.Ordonance;
import com.grp10.e_pharmacy.domain.Pharmacie;
import com.grp10.e_pharmacy.domain.User;
import com.grp10.e_pharmacy.model.CommandeDTO;
import com.grp10.e_pharmacy.repos.CommandeRepository;
import com.grp10.e_pharmacy.repos.OrdonanceRepository;
import com.grp10.e_pharmacy.repos.PharmacieRepository;
import com.grp10.e_pharmacy.repos.UserRepository;
import com.grp10.e_pharmacy.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final PharmacieRepository pharmacieRepository;
    private final OrdonanceRepository ordonanceRepository;
    private final UserRepository userRepository;

    public CommandeService(final CommandeRepository commandeRepository,
            final PharmacieRepository pharmacieRepository,
            final OrdonanceRepository ordonanceRepository, final UserRepository userRepository) {
        this.commandeRepository = commandeRepository;
        this.pharmacieRepository = pharmacieRepository;
        this.ordonanceRepository = ordonanceRepository;
        this.userRepository = userRepository;
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
        commandeDTO.setOrdonance(commande.getOrdonance() == null ? null : commande.getOrdonance().getId());
        commandeDTO.setPatient(commande.getPatient() == null ? null : commande.getPatient().getId());
        commandeDTO.setLivreur(commande.getLivreur() == null ? null : commande.getLivreur().getId());
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
        final Ordonance ordonance = commandeDTO.getOrdonance() == null ? null : ordonanceRepository.findById(commandeDTO.getOrdonance())
                .orElseThrow(() -> new NotFoundException("ordonance not found"));
        commande.setOrdonance(ordonance);
        final User patient = commandeDTO.getPatient() == null ? null : userRepository.findById(commandeDTO.getPatient())
                .orElseThrow(() -> new NotFoundException("patient not found"));
        commande.setPatient(patient);
        final User livreur = commandeDTO.getLivreur() == null ? null : userRepository.findById(commandeDTO.getLivreur())
                .orElseThrow(() -> new NotFoundException("livreur not found"));
        commande.setLivreur(livreur);
        return commande;
    }

    public boolean ordonanceExists(final Long id) {
        return commandeRepository.existsByOrdonanceId(id);
    }

}
