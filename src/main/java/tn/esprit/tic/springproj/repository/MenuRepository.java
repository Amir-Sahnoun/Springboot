package tn.esprit.tic.springproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tic.springproj.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}