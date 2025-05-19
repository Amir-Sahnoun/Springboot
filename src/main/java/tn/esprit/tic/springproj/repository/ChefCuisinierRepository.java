package tn.esprit.tic.springproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.tic.springproj.model.ChefCuisinier;
import tn.esprit.tic.springproj.model.TypeChef;

import java.util.List;

public interface ChefCuisinierRepository extends JpaRepository<ChefCuisinier, Long> {

    @Query("SELECT c FROM ChefCuisinier c JOIN c.menus m JOIN m.restaurant r WHERE c.typeChef = :typeChef AND r.nom = :nomRestaurant")
    List<ChefCuisinier> findByTypeChefAndRestaurant(@Param("typeChef") TypeChef typeChef, @Param("nomRestaurant") String nomRestaurant);

}