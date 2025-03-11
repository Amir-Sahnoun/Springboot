package tn.esprit.tic.springproj.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    private String identifiant;
    private Date datePremiereVisite;

}
