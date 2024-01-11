package com.grp10.e_pharmacy.model;

import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CommandeDTO {

    private Long id;

    private LocalDate date;

    private Double prix;

    private Boolean livraison;

    @Size(max = 255)
    private String addressLivraison;

    private Statue statue;

    private Long pharmacie;

    private Long patient;

    private Long livreur;

    private List<Long> medicaments;

}
