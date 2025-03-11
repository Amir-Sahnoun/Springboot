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

    @OneToMany(mappedBy = "chainRestaurant")
    private List<Restaurant> restaurants;

    public Long getIdChainRestaurant() {
        return idChainRestaurant;
    }

    public void setIdChainRestaurant(Long idChainRestaurant) {
        this.idChainRestaurant = idChainRestaurant;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

}
