package com.grp10.e_pharmacy.domain;

import com.grp10.e_pharmacy.model.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "\"User\"")
@Getter
@Setter
public class User {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column
    private String nom;

    @Column
    private Role role;

    @OneToMany(mappedBy = "patient")
    private Set<Ordonance> ordonances;

    @OneToMany(mappedBy = "patient")
    private Set<Commande> commandes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "panier_id", unique = true)
    private Commande panier;

}
