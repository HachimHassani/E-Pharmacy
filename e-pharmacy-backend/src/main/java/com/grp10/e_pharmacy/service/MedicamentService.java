package com.grp10.e_pharmacy.service;

import com.grp10.e_pharmacy.domain.Medicament;
import com.grp10.e_pharmacy.domain.Ordonance;
import com.grp10.e_pharmacy.model.MedicamentDTO;
import com.grp10.e_pharmacy.repos.MedicamentRepository;
import com.grp10.e_pharmacy.repos.OrdonanceRepository;
import com.grp10.e_pharmacy.util.NotFoundException;
import com.grp10.e_pharmacy.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class MedicamentService {

    private final MedicamentRepository medicamentRepository;
    private final OrdonanceRepository ordonanceRepository;

    public MedicamentService(final MedicamentRepository medicamentRepository,
            final OrdonanceRepository ordonanceRepository) {
        this.medicamentRepository = medicamentRepository;
        this.ordonanceRepository = ordonanceRepository;
    }

    public List<MedicamentDTO> findAll() {
        final List<Medicament> medicaments = medicamentRepository.findAll(Sort.by("id"));
        return medicaments.stream()
                .map(medicament -> mapToDTO(medicament, new MedicamentDTO()))
                .toList();
    }

    public MedicamentDTO get(final Long id) {
        return medicamentRepository.findById(id)
                .map(medicament -> mapToDTO(medicament, new MedicamentDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final MedicamentDTO medicamentDTO) {
        final Medicament medicament = new Medicament();
        mapToEntity(medicamentDTO, medicament);
        return medicamentRepository.save(medicament).getId();
    }

    public void update(final Long id, final MedicamentDTO medicamentDTO) {
        final Medicament medicament = medicamentRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(medicamentDTO, medicament);
        medicamentRepository.save(medicament);
    }

    public void delete(final Long id) {
        final Medicament medicament = medicamentRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        // remove many-to-many relations at owning side
        ordonanceRepository.findAllByMedicaments(medicament)
                .forEach(ordonance -> ordonance.getMedicaments().remove(medicament));
        medicamentRepository.delete(medicament);
    }

    private MedicamentDTO mapToDTO(final Medicament medicament, final MedicamentDTO medicamentDTO) {
        medicamentDTO.setId(medicament.getId());
        medicamentDTO.setNom(medicament.getNom());
        medicamentDTO.setPrix(medicament.getPrix());
        return medicamentDTO;
    }

    private Medicament mapToEntity(final MedicamentDTO medicamentDTO, final Medicament medicament) {
        medicament.setNom(medicamentDTO.getNom());
        medicament.setPrix(medicamentDTO.getPrix());
        return medicament;
    }

    public boolean nomExists(final String nom) {
        return medicamentRepository.existsByNomIgnoreCase(nom);
    }

    public String getReferencedWarning(final Long id) {
        final Medicament medicament = medicamentRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final Ordonance medicamentsOrdonance = ordonanceRepository.findFirstByMedicaments(medicament);
        if (medicamentsOrdonance != null) {
            return WebUtils.getMessage("medicament.ordonance.medicaments.referenced", medicamentsOrdonance.getId());
        }
        return null;
    }

}
