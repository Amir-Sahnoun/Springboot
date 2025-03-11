package tn.esprit.tic.springproj.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Composant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComposant;
    private String nomComposant;
    private Float prix;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @OneToOne(mappedBy = "composant")
    private DetailComposant detailComposant;

}