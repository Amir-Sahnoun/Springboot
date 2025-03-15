package tn.esprit.tic.springproj.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    private String identifiant;
    private Date datePremiereVisite;

    @OneToMany(mappedBy = "client")
    private List<Commande> commandes;

}
