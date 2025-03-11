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

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menu")
    private List<Composant> composants;

    @OneToMany(mappedBy = "menu")
    private List<Commande> commandes;

    @ManyToMany
    @JoinTable(
            name = "menu_chefCuisinier",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "chefCuisinier_id")
    )
    private List<ChefCuisinier> chefCuisiniers;

    // Getters and Setters
}