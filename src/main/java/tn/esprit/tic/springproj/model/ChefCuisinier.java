package tn.esprit.tic.springproj.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class ChefCuisinier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChefCuisinier;
    private String nom;
    private String prenom;

    @Enumerated(EnumType.STRING)
    private TypeChef typeChef;

    @ManyToMany(mappedBy = "chefCuisiniers")
    private List<Menu> menus;

}
