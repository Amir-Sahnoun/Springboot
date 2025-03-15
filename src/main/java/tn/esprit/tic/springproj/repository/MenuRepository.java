package tn.esprit.tic.springproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.tic.springproj.model.Menu;
import tn.esprit.tic.springproj.model.TypeComposant;
import tn.esprit.tic.springproj.model.TypeMenu;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Query("SELECT m FROM Menu m JOIN m.composants c GROUP BY m HAVING SUM(c.prix) > :montant AND m.typeMenu = :typeMenu")
    List<Menu> findByTypeMenuAndPrixTotalGreaterThan(@Param("typeMenu") TypeMenu typeMenu, @Param("montant") Float montant);
    @Query("SELECT m.libelleMenu FROM Menu m WHERE m.typeMenu = :typeMenu ORDER BY m.prixTotal")
    List<String> findNomByTypeMenuOrderByPrixTotal(@Param("typeMenu") TypeMenu typeMenu);
    @Query("SELECT DISTINCT m FROM Menu m JOIN m.composants c JOIN c.detailComposant d WHERE d.typeComposant = :typeComposant")
    List<Menu> findByTypeComposant(@Param("typeComposant") TypeComposant typeComposant);

    // 1.4 Liste des menus selon le type menu avec le prix total des composants est supérieur à un montant donné en paramètres
    @Query("SELECT m FROM Menu m WHERE m.typeMenu = :typeMenu AND m.prixTotal > :montant")
    List<Menu> findMenusByTypeAndPrixTotalComposantsGreaterThan(@Param("typeMenu") String typeMenu, @Param("montant") Float montant);

    // 2.1 List nom Menu Par le type Menu Ordonne Par PrixTotal
    @Query("SELECT m.libelleMenu FROM Menu m WHERE m.typeMenu = :typeMenu ORDER BY m.prixTotal ASC")
    List<String> findMenuNamesByTypeOrderedByPrixTotal(@Param("typeMenu") String typeMenu);

    // 2.2 List Menu Par TypeComposant (exemple : les menus contenant de la viande blanche)
    @Query("SELECT m FROM Menu m JOIN m.composants c JOIN c.detailComposant d WHERE d.typeComposant = :typeComposant")
    List<Menu> findMenusByTypeComposant(@Param("typeComposant") TypeComposant typeComposant);

    Menu findByLibelleMenu(String libelleMenu);
}
