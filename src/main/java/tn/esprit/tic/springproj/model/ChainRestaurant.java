package tn.esprit.tic.springproj.model;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class ChainRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChainRestaurant;
    private String libelle;
    private LocalDate dateCreation;

}
