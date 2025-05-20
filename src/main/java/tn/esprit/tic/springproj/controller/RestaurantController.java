package tn.esprit.tic.springproj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tic.springproj.model.*;
import tn.esprit.tic.springproj.repository.ChefCuisinierRepository;
import tn.esprit.tic.springproj.repository.MenuRepository;
import tn.esprit.tic.springproj.service.RestaurantService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private ChefCuisinierRepository ChefCuisinierRepository;
    @Autowired
    private MenuRepository MenuRepository;

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
    @PostMapping("/listChefCuisinierByTypeChefAndRestaurant")
    public List<ChefCuisinier> listChefCuisinierByTypeChefAndRestaurant(TypeChef typeChef, String nomRestaurant) {
        return ChefCuisinierRepository.findByTypeChefAndRestaurant(typeChef, nomRestaurant);
    }
    @PostMapping("/listMenuSelonTypeMenuEtprixComposantsSuperieurAUnMontant")
    public List<Menu> listeMenuSelonTypeMenuEtprixComposantsSuperieurAUnMontant(TypeMenu typeMenu, Float prixTotal) {
        return MenuRepository.findByTypeMenuAndPrixTotalGreaterThan(typeMenu, prixTotal);
    }
    @PostMapping("/ajoutComposantsEtMiseAjourPrixMenu")
    public Menu ajoutComposantsEtMiseAjourPrixMenu(Set<Composant> composants, Long idMenu) {
        // Retrieve the menu by its ID
        Menu menu = MenuRepository.findById(idMenu).orElseThrow(() -> new IllegalArgumentException("Menu not found"));
        // Add components to the menu
        menu.getComposants().addAll(composants);
        // Update the total price of the menu
        float totalPrice = 0;
        for (Composant component : composants) {
            totalPrice += component.getPrix();
        }
        menu.setPrixTotal(totalPrice);
        return MenuRepository.save(menu);
    }
}