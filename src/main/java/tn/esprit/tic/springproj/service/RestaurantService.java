package tn.esprit.tic.springproj.service;

import org.springframework.stereotype.Service;
import tn.esprit.tic.springproj.model.ChefCuisinier;
import tn.esprit.tic.springproj.model.Commande;
import tn.esprit.tic.springproj.model.Restaurant;

@Service
public interface RestaurantService extends CrudService<Restaurant> {
    Restaurant affecterRestaurantAChaineRestauration(String nomRestaurant, String libelleChaine);
    Restaurant ajoutRestaurantEtMenuAssocies(Restaurant restaurant);
    ChefCuisinier affecterChefCuisinierAMenu(Long idChefCuisinier, Long idMenu);
    ChefCuisinier desaffecterChefCuisinierDuMenu(Long idMenu, Long idChefCuisinier);
    void ajouterCommandeEtaffecterAClientEtMenu(Commande commande, String identifiant, String libelleMenu);
    Commande affecterNoteACommande(Long idCommande, Long note);

}