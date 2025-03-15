package tn.esprit.tic.springproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tic.springproj.model.ChefCuisinier;
import tn.esprit.tic.springproj.model.Commande;
import tn.esprit.tic.springproj.model.Restaurant;
import tn.esprit.tic.springproj.service.RestaurantService;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/affecter")
    public Restaurant affecterRestaurantAChaineRestauration(@RequestParam String nomRestaurant, @RequestParam String libelleChaine) {
        return restaurantService.affecterRestaurantAChaineRestauration(nomRestaurant, libelleChaine);
    }

      @PostMapping("/ajout")
    public Restaurant ajoutRestaurantEtMenuAssocies(@RequestBody Restaurant restaurant) {
        return restaurantService.ajoutRestaurantEtMenuAssocies(restaurant);
    }

    @PostMapping("/affecterChef")
    public ChefCuisinier affecterChefCuisinierAMenu(@RequestParam Long idChefCuisinier, @RequestParam Long idMenu) {
        return restaurantService.affecterChefCuisinierAMenu(idChefCuisinier, idMenu);
    }

    @PostMapping("/desaffecterChef")
    public ChefCuisinier desaffecterChefCuisinierDuMenu(@RequestParam Long idMenu, @RequestParam Long idChefCuisinier) {
        return restaurantService.desaffecterChefCuisinierDuMenu(idMenu, idChefCuisinier);
    }

    @PostMapping("/ajouterCommande")
    public void ajouterCommandeEtaffecterAClientEtMenu(@RequestBody Commande commande, @RequestParam String identifiant, @RequestParam String libelleMenu) {
        restaurantService.ajouterCommandeEtaffecterAClientEtMenu(commande, identifiant, libelleMenu);
    }

    @PostMapping("/affecterNote")
    public Commande affecterNoteACommande(@RequestParam Long idCommande, @RequestParam Long note) {
        return restaurantService.affecterNoteACommande(idCommande, note);
    }
}