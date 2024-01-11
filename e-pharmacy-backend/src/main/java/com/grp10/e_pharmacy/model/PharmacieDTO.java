package com.grp10.e_pharmacy.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PharmacieDTO {

    private Long id;

    @Size(max = 255)
    private String nom;

    @Size(max = 255)
    private String address;

}
