package com.grp10.e_pharmacy.controller;

import com.grp10.e_pharmacy.model.OrdonanceDTO;
import com.grp10.e_pharmacy.service.OrdonanceService;
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
@RequestMapping(value = "/api/ordonances", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrdonanceResource {

    private final OrdonanceService ordonanceService;

    public OrdonanceResource(final OrdonanceService ordonanceService) {
        this.ordonanceService = ordonanceService;
    }

    @GetMapping
    public ResponseEntity<List<OrdonanceDTO>> getAllOrdonances() {
        return ResponseEntity.ok(ordonanceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdonanceDTO> getOrdonance(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(ordonanceService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createOrdonance(
            @RequestBody @Valid final OrdonanceDTO ordonanceDTO) {
        final Long createdId = ordonanceService.create(ordonanceDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateOrdonance(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final OrdonanceDTO ordonanceDTO) {
        ordonanceService.update(id, ordonanceDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrdonance(@PathVariable(name = "id") final Long id) {
        ordonanceService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
