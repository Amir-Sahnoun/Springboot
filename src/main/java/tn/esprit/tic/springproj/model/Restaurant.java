package tn.esprit.tic.springproj.model;

import jakarta.persistence.*;
import lombok.Setter;

import java.util.List;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRestaurant;
    @Setter
    private String nom;
    @Setter
    private Long nbPlacesMax;

    @Setter
    @ManyToOne
    @JoinColumn(name = "chainRestaurant_id")
    private ChainRestaurant chainRestaurant;

    @Setter
    @OneToMany(mappedBy = "restaurant")
    private List<Menu> menus;

    public List<Menu> getMenus() {
        return menus;
    }

    public ChainRestaurant getChainRestaurant() {
        return chainRestaurant;
    }

    public Long getNbPlacesMax() {
        return nbPlacesMax;
    }

    public String getNom() {
        return nom;
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long id) {


    }
}