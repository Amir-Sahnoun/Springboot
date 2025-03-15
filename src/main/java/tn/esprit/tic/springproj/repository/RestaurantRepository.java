package tn.esprit.tic.springproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.tic.springproj.model.Restaurant;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByNbPlacesMaxGreaterThanAndChainRestaurantDateCreationBefore(Long capacite, LocalDate dateCreation);

     // 1.5 Liste des restaurants ayant une capacit� sup�rieur � 100 (param�tre) et dont la chaine de restauration a �t� cr��e avant 2020 (param�tre)
    @Query("SELECT r FROM Restaurant r WHERE r.nbPlacesMax > :capacite AND r.chainRestaurant.dateCreation < :date")
    List<Restaurant> findRestaurantsByCapaciteAndChaineRestaurationDate(@Param("capacite") Long capacite, @Param("date") Date date);
    
    Restaurant findByNom(String nom);

}