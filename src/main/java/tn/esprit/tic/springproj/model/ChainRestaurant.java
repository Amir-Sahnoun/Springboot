package tn.esprit.tic.springproj.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@Entity
public class ChainRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChainRestaurant;
    private String libelleChaine;
    private LocalDate dateCreation;

    @OneToMany(mappedBy = "chainRestaurant")
    private List<Restaurant> restaurants;

}
