package com.grp10.e_pharmacy.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MedicamentDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String nom;

    private Long prix;

}
