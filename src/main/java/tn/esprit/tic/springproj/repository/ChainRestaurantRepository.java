package tn.esprit.tic.springproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tic.springproj.model.ChainRestaurant;

public interface ChainRestaurantRepository extends JpaRepository<ChainRestaurant, Long> {
	
	ChainRestaurant findByLibelleChaine(String libelleChaine);

}