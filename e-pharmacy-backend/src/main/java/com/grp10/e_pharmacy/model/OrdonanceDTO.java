package com.grp10.e_pharmacy.model;

import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrdonanceDTO {

    private Long id;

    private LocalDate date;

    @Size(max = 255)
    private String statue;

    private List<Long> medicaments;

    private Long doctor;

    private Long patient;

    private Long commande;

}
