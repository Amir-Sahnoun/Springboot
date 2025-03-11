package tn.esprit.tic.springproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tic.springproj.model.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}