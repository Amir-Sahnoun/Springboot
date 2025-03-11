package tn.esprit.tic.springproj.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRestaurant;
    private String nom;
    private Long nbPlacesMax;

    @ManyToOne
    @JoinColumn(name = "chainRestaurant_id")
    private ChainRestaurant chainRestaurant;

    @OneToMany(mappedBy = "restaurant")
    private List<Menu> menus;
}