package com.grp10.e_pharmacy.service;

import com.grp10.e_pharmacy.domain.Commande;
import com.grp10.e_pharmacy.domain.Medicament;
import com.grp10.e_pharmacy.domain.Ordonance;
import com.grp10.e_pharmacy.domain.User;
import com.grp10.e_pharmacy.model.OrdonanceDTO;
import com.grp10.e_pharmacy.repos.CommandeRepository;
import com.grp10.e_pharmacy.repos.MedicamentRepository;
import com.grp10.e_pharmacy.repos.OrdonanceRepository;
import com.grp10.e_pharmacy.repos.UserRepository;
import com.grp10.e_pharmacy.util.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class OrdonanceService {

    private final OrdonanceRepository ordonanceRepository;
    private final MedicamentRepository medicamentRepository;
    private final UserRepository userRepository;
    private final CommandeRepository commandeRepository;

    public OrdonanceService(final OrdonanceRepository ordonanceRepository,
            final MedicamentRepository medicamentRepository, final UserRepository userRepository,
            final CommandeRepository commandeRepository) {
        this.ordonanceRepository = ordonanceRepository;
        this.medicamentRepository = medicamentRepository;
        this.userRepository = userRepository;
        this.commandeRepository = commandeRepository;
    }

    public List<OrdonanceDTO> findAll() {
        final List<Ordonance> ordonances = ordonanceRepository.findAll(Sort.by("id"));
        return ordonances.stream()
                .map(ordonance -> mapToDTO(ordonance, new OrdonanceDTO()))
                .toList();
    }

    public OrdonanceDTO get(final Long id) {
        return ordonanceRepository.findById(id)
                .map(ordonance -> mapToDTO(ordonance, new OrdonanceDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final OrdonanceDTO ordonanceDTO) {
        final Ordonance ordonance = new Ordonance();
        mapToEntity(ordonanceDTO, ordonance);
        return ordonanceRepository.save(ordonance).getId();
    }

    public void update(final Long id, final OrdonanceDTO ordonanceDTO) {
        final Ordonance ordonance = ordonanceRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(ordonanceDTO, ordonance);
        ordonanceRepository.save(ordonance);
    }

    public void delete(final Long id) {
        ordonanceRepository.deleteById(id);
    }

    private OrdonanceDTO mapToDTO(final Ordonance ordonance, final OrdonanceDTO ordonanceDTO) {
        ordonanceDTO.setId(ordonance.getId());
        ordonanceDTO.setDate(ordonance.getDate());
        ordonanceDTO.setStatue(ordonance.getStatue());
        ordonanceDTO.setMedicaments(ordonance.getMedicaments().stream()
                .map(medicament -> medicament.getId())
                .toList());
        ordonanceDTO.setDoctor(ordonance.getDoctor() == null ? null : ordonance.getDoctor().getId());
        ordonanceDTO.setPatient(ordonance.getPatient() == null ? null : ordonance.getPatient().getId());
        ordonanceDTO.setCommande(ordonance.getCommande() == null ? null : ordonance.getCommande().getId());
        return ordonanceDTO;
    }

    private Ordonance mapToEntity(final OrdonanceDTO ordonanceDTO, final Ordonance ordonance) {
        ordonance.setDate(ordonanceDTO.getDate());
        ordonance.setStatue(ordonanceDTO.getStatue());
        final List<Medicament> medicaments = medicamentRepository.findAllById(
                ordonanceDTO.getMedicaments() == null ? Collections.emptyList() : ordonanceDTO.getMedicaments());
        if (medicaments.size() != (ordonanceDTO.getMedicaments() == null ? 0 : ordonanceDTO.getMedicaments().size())) {
            throw new NotFoundException("one of medicaments not found");
        }
        ordonance.setMedicaments(medicaments.stream().collect(Collectors.toSet()));
        final User doctor = ordonanceDTO.getDoctor() == null ? null : userRepository.findById(ordonanceDTO.getDoctor())
                .orElseThrow(() -> new NotFoundException("doctor not found"));
        ordonance.setDoctor(doctor);
        final User patient = ordonanceDTO.getPatient() == null ? null : userRepository.findById(ordonanceDTO.getPatient())
                .orElseThrow(() -> new NotFoundException("patient not found"));
        ordonance.setPatient(patient);
        final Commande commande = ordonanceDTO.getCommande() == null ? null : commandeRepository.findById(ordonanceDTO.getCommande())
                .orElseThrow(() -> new NotFoundException("commande not found"));
        ordonance.setCommande(commande);
        return ordonance;
    }

}
