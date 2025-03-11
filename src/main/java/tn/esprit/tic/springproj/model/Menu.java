package tn.esprit.tic.springproj.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMenu;
    private String libelleMenu;

    @Enumerated(EnumType.STRING)
    private TypeMenu typeMenu;
    private Float prixTotal;



    // Getters and Setters
}