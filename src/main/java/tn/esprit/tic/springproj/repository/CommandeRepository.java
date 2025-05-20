package tn.esprit.tic.springproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.tic.springproj.model.Commande;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByClientIdClient(Long clientId);
    List<Commande> findByClientIdClientAndDateCommandeBetween(Long clientId, LocalDate startDate, LocalDate endDate);
    List<Commande> findByDateCommandeBetweenOrderByTotalCommandeAsc(LocalDate startDate, LocalDate endDate);


    // 1.1 Liste des commandes d'un client
    @Query("SELECT c FROM Commande c WHERE c.client.idClient = :clientId")
    List<Commande> findCommandesByClientId(@Param("clientId") Long clientId);
    
    // 1.2 Liste des commandes d'un client dont la date commande entre deux dates
    @Query("SELECT c FROM Commande c WHERE c.client.idClient = :clientId AND c.dateCommande BETWEEN :startDate AND :endDate")
    List<Commande> findCommandesByClientIdAndDateRange(@Param("clientId") Long clientId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    // 1.3 Liste des commandes dont la date commande entre deux dates ordonn� par note prix croissant
    @Query("SELECT c FROM Commande c JOIN c.menu m WHERE c.dateCommande BETWEEN :startDate AND :endDate ORDER BY m.prixTotal ASC")
    List<Commande> findCommandesByDateRangeOrderedByPrix(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT c FROM Commande c WHERE c.dateCommande < :cutoffTime")
    List<Commande> findOrdersBefore(@Param("cutoffTime") LocalDateTime cutoffTime);
}