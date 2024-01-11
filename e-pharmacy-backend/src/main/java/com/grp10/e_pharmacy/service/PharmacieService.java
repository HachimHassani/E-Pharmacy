package com.grp10.e_pharmacy.service;

import com.grp10.e_pharmacy.domain.Commande;
import com.grp10.e_pharmacy.domain.Pharmacie;
import com.grp10.e_pharmacy.model.PharmacieDTO;
import com.grp10.e_pharmacy.repos.CommandeRepository;
import com.grp10.e_pharmacy.repos.PharmacieRepository;
import com.grp10.e_pharmacy.util.NotFoundException;
import com.grp10.e_pharmacy.util.WebUtils;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PharmacieService {

    private final PharmacieRepository pharmacieRepository;
    private final CommandeRepository commandeRepository;

    public PharmacieService(final PharmacieRepository pharmacieRepository,
            final CommandeRepository commandeRepository) {
        this.pharmacieRepository = pharmacieRepository;
        this.commandeRepository = commandeRepository;
    }

    public List<PharmacieDTO> findAll() {
        final List<Pharmacie> pharmacies = pharmacieRepository.findAll(Sort.by("id"));
        return pharmacies.stream()
                .map(pharmacie -> mapToDTO(pharmacie, new PharmacieDTO()))
                .toList();
    }

    public PharmacieDTO get(final Long id) {
        return pharmacieRepository.findById(id)
                .map(pharmacie -> mapToDTO(pharmacie, new PharmacieDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final PharmacieDTO pharmacieDTO) {
        final Pharmacie pharmacie = new Pharmacie();
        mapToEntity(pharmacieDTO, pharmacie);
        return pharmacieRepository.save(pharmacie).getId();
    }

    public void update(final Long id, final PharmacieDTO pharmacieDTO) {
        final Pharmacie pharmacie = pharmacieRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(pharmacieDTO, pharmacie);
        pharmacieRepository.save(pharmacie);
    }

    public void delete(final Long id) {
        pharmacieRepository.deleteById(id);
    }

    private PharmacieDTO mapToDTO(final Pharmacie pharmacie, final PharmacieDTO pharmacieDTO) {
        pharmacieDTO.setId(pharmacie.getId());
        pharmacieDTO.setNom(pharmacie.getNom());
        pharmacieDTO.setAddress(pharmacie.getAddress());
        return pharmacieDTO;
    }

    private Pharmacie mapToEntity(final PharmacieDTO pharmacieDTO, final Pharmacie pharmacie) {
        pharmacie.setNom(pharmacieDTO.getNom());
        pharmacie.setAddress(pharmacieDTO.getAddress());
        return pharmacie;
    }

    public String getReferencedWarning(final Long id) {
        final Pharmacie pharmacie = pharmacieRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Commande pharmacieCommande = commandeRepository.findFirstByPharmacie(pharmacie);
        if (pharmacieCommande != null) {
            return WebUtils.getMessage("pharmacie.commande.pharmacie.referenced", pharmacieCommande.getId());
        }
        return null;
    }

}
