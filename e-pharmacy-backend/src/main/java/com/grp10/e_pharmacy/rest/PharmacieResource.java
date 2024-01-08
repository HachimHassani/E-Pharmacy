package com.grp10.e_pharmacy.rest;

import com.grp10.e_pharmacy.model.PharmacieDTO;
import com.grp10.e_pharmacy.service.PharmacieService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/pharmacies", produces = MediaType.APPLICATION_JSON_VALUE)
public class PharmacieResource {

    private final PharmacieService pharmacieService;

    public PharmacieResource(final PharmacieService pharmacieService) {
        this.pharmacieService = pharmacieService;
    }

    @GetMapping
    public ResponseEntity<List<PharmacieDTO>> getAllPharmacies() {
        return ResponseEntity.ok(pharmacieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PharmacieDTO> getPharmacie(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(pharmacieService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createPharmacie(
            @RequestBody @Valid final PharmacieDTO pharmacieDTO) {
        final Long createdId = pharmacieService.create(pharmacieDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updatePharmacie(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final PharmacieDTO pharmacieDTO) {
        pharmacieService.update(id, pharmacieDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePharmacie(@PathVariable(name = "id") final Long id) {
        pharmacieService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
