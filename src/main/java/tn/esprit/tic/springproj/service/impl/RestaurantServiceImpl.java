package tn.esprit.tic.springproj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tic.springproj.model.*;
import tn.esprit.tic.springproj.repository.*;
import tn.esprit.tic.springproj.service.RestaurantService;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ChainRestaurantRepository chainRestaurantRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ChefCuisinierRepository chefCuisinierRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    // CRUD Methods
    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<Restaurant> getById(Long id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public Restaurant save(Restaurant entity) {
        return restaurantRepository.save(entity);
    }

    @Override
    public Restaurant update(Long id, Restaurant entity) {
        if (!restaurantRepository.existsById(id)) {
            throw new IllegalArgumentException("Restaurant with ID " + id + " not found");
        }
        entity.setIdRestaurant(id); // Matches 'idRestaurant' in Restaurant entity
        return restaurantRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new IllegalArgumentException("Restaurant with ID " + id + " not found");
        }
        restaurantRepository.deleteById(id);
    }

    @Override
    public Restaurant affecterRestaurantAChaineRestauration(String nomRestaurant, String libelleChaine) {
        Restaurant restaurant = restaurantRepository.findByNom(nomRestaurant);
        ChainRestaurant chainRestaurant = chainRestaurantRepository.findByLibelleChaine(libelleChaine);

        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurant with name " + nomRestaurant + " not found");
        }
        if (chainRestaurant == null) {
            throw new IllegalArgumentException("ChainRestaurant with libelle " + libelleChaine + " not found");
        }

        restaurant.setChainRestaurant(chainRestaurant);
        // Optional: Update the inverse side (not required if cascading is configured)
        // chainRestaurant.getRestaurants().add(restaurant);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant ajoutRestaurantEtMenuAssocies(Restaurant restaurant) {
        if (restaurant == null) {
            throw new IllegalArgumentException("Restaurant cannot be null");
        }
        List<Menu> menus = restaurant.getMenus();
        if (menus != null && !menus.isEmpty()) {
            for (Menu menu : menus) {
                menu.setPrixTotal(0.0F); // Overwrites to 0.0F
                menu.setRestaurant(restaurant); // Sets the relationship
            }
        }
        return restaurantRepository.save(restaurant); // Only saves restaurant
    }

    @Override
    public ChefCuisinier affecterChefCuisinierAMenu(Long idChefCuisinier, Long idMenu) {
        Optional<ChefCuisinier> chefCuisinierOpt = chefCuisinierRepository.findById(idChefCuisinier);
        Optional<Menu> menuOpt = menuRepository.findById(idMenu);

        if (!chefCuisinierOpt.isPresent()) {
            throw new IllegalArgumentException("ChefCuisinier with ID " + idChefCuisinier + " not found");
        }
        if (!menuOpt.isPresent()) {
            throw new IllegalArgumentException("Menu with ID " + idMenu + " not found");
        }

        ChefCuisinier chefCuisinier = chefCuisinierOpt.get();
        Menu menu = menuOpt.get();

        // Ensure no duplicates in the many-to-many relationship
        if (!menu.getChefCuisiniers().contains(chefCuisinier)) {
            menu.getChefCuisiniers().add(chefCuisinier);
            chefCuisinier.getMenus().add(menu); // Update both sides of the relationship
        }

        menuRepository.save(menu); // Save the updated menu with the relationship
        return chefCuisinierRepository.save(chefCuisinier);
    }

    @Override
    public ChefCuisinier desaffecterChefCuisinierDuMenu(Long idMenu, Long idChefCuisinier) {
        Optional<Menu> menuOpt = menuRepository.findById(idMenu);
        Optional<ChefCuisinier> chefCuisinierOpt = chefCuisinierRepository.findById(idChefCuisinier);

        if (!menuOpt.isPresent()) {
            throw new IllegalArgumentException("Menu with ID " + idMenu + " not found");
        }
        if (!chefCuisinierOpt.isPresent()) {
            throw new IllegalArgumentException("ChefCuisinier with ID " + idChefCuisinier + " not found");
        }

        Menu menu = menuOpt.get();
        ChefCuisinier chefCuisinier = chefCuisinierOpt.get();

        menu.getChefCuisiniers().remove(chefCuisinier);
        chefCuisinier.getMenus().remove(menu); // Update both sides of the relationship

        menuRepository.save(menu);
        return chefCuisinierRepository.save(chefCuisinier);
    }

    @Override
    public void ajouterCommandeEtaffecterAClientEtMenu(Commande commande, String identifiant, String libelleMenu) {
        if (commande == null) {
            throw new IllegalArgumentException("Commande cannot be null");
        }

        Client client = clientRepository.findByIdentifiant(identifiant);
        Menu menu = menuRepository.findByLibelleMenu(libelleMenu);

        if (client == null) {
            throw new IllegalArgumentException("Client with identifiant " + identifiant + " not found");
        }
        if (menu == null) {
            throw new IllegalArgumentException("Menu with libelle " + libelleMenu + " not found");
        }

        commande.setClient(client);
        commande.setMenu(menu);

        float prixMenu = menu.getPrixTotal();
        int pourcentageRemise = commande.getPourcentageRemise() != null ? commande.getPourcentageRemise() : 0;
        float totalRemise = prixMenu * pourcentageRemise / 100;
        float totalCommande = prixMenu - totalRemise;

        commande.setTotalRemise(totalRemise);
        commande.setTotalCommande(totalCommande);

        // Optional: Update inverse relationships
        // client.getCommandes().add(commande);
        // menu.getCommandes().add(commande);

        commandeRepository.save(commande);
    }

    @Override
    public Commande affecterNoteACommande(Long idCommande, Long note) {
        Optional<Commande> commandeOpt = commandeRepository.findById(idCommande);

        if (!commandeOpt.isPresent()) {
            throw new IllegalArgumentException("Commande with ID " + idCommande + " not found");
        }

        Commande commande = commandeOpt.get();
        if (note < 0 || note > 5) {
            throw new IllegalArgumentException("Note must be between 0 and 5");
        }

        commande.setNote(note);
        return commandeRepository.save(commande);
    }
}