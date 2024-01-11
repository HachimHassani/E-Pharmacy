package com.grp10.e_pharmacy.controller;

import com.grp10.e_pharmacy.model.CommandeDTO;
import com.grp10.e_pharmacy.service.CommandeService;
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
@RequestMapping(value = "/api/commandes", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommandeResource {

    private final CommandeService commandeService;

    public CommandeResource(final CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping
    public ResponseEntity<List<CommandeDTO>> getAllCommandes() {
        return ResponseEntity.ok(commandeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeDTO> getCommande(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(commandeService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createCommande(@RequestBody @Valid final CommandeDTO commandeDTO) {
        final Long createdId = commandeService.create(commandeDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/confirm")
    public ResponseEntity<Void> confirmCommande(@PathVariable(name = "id") final Long id) {
        commandeService.confirm(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCommande(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final CommandeDTO commandeDTO) {
        commandeService.update(id, commandeDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable(name = "id") final Long id) {
        commandeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
