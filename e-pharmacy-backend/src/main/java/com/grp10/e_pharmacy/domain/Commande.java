package com.grp10.e_pharmacy.domain;

import com.grp10.e_pharmacy.model.Statue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Commande {

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
    private LocalDate date;

    @Column
    private Double prix;

    @Column
    private Boolean livraison;

    @Column
    private String addressLivraison;

    @Column
    @Enumerated(EnumType.STRING)
    private Statue statue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pharmacie_id")
    private Pharmacie pharmacie;

    @OneToMany(mappedBy = "commande")
    private Set<Ordonance> ordonances;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private User patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livreur_id")
    private User livreur;

    @ManyToMany
    @JoinTable(
            name = "CommandeMed",
            joinColumns = @JoinColumn(name = "commandeId"),
            inverseJoinColumns = @JoinColumn(name = "medicamentId")
    )
    private Set<Medicament> medicaments;

}
