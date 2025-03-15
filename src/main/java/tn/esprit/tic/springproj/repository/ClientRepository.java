package tn.esprit.tic.springproj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tic.springproj.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	    Client findByIdentifiant(String identifiant);
}