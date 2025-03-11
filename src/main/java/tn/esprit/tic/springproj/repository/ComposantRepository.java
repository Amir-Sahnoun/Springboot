package tn.esprit.tic.springproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tic.springproj.model.Composant;

public interface ComposantRepository extends JpaRepository<Composant, Long> {
}