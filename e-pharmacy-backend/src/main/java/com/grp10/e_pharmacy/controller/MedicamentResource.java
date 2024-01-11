package com.grp10.e_pharmacy.controller;

import com.grp10.e_pharmacy.model.MedicamentDTO;
import com.grp10.e_pharmacy.service.MedicamentService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/medicaments", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicamentResource {

    private final MedicamentService medicamentService;

    public MedicamentResource(final MedicamentService medicamentService) {
        this.medicamentService = medicamentService;
    }

    @GetMapping
    public ResponseEntity<List<MedicamentDTO>> getAllMedicaments() {
        return ResponseEntity.ok(medicamentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentDTO> getMedicament(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(medicamentService.get(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MedicamentDTO>> searchMedicamentsByName(@RequestParam(name = "name") String name) {
        List<MedicamentDTO> searchResults = medicamentService.searchByName(name);
        return ResponseEntity.ok(searchResults);
    }

    @PostMapping
    public ResponseEntity<Long> createMedicament(
            @RequestBody @Valid final MedicamentDTO medicamentDTO) {
        final Long createdId = medicamentService.create(medicamentDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateMedicament(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final MedicamentDTO medicamentDTO) {
        medicamentService.update(id, medicamentDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicament(@PathVariable(name = "id") final Long id) {
        medicamentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
