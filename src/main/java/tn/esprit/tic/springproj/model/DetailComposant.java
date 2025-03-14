package tn.esprit.tic.springproj.model;

import jakarta.persistence.*;


@Entity
public class DetailComposant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetailComposant;
    private Float imc;

    @Enumerated(EnumType.STRING)
    private TypeComposant typeComposant;

    @OneToOne
    @JoinColumn(name = "composant_id")
    private Composant composant;

    public void setIdDetailComposant(Long id) {

    }
}