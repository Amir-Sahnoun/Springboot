package tn.esprit.tic.springproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tic.springproj.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
