package tn.esprit.tic.springproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tic.springproj.model.DetailComposant;

public interface DetailComposantRepository extends JpaRepository<DetailComposant, Long> {
}