package tn.esprit.tic.springproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tic.springproj.model.ChefCuisinier;

public interface ChefCuisinierRepository extends JpaRepository<ChefCuisinier, Long> {
}