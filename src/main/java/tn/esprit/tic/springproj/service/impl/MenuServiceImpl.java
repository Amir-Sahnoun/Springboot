package tn.esprit.tic.springproj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tic.springproj.model.Composant;
import tn.esprit.tic.springproj.model.Menu;
import tn.esprit.tic.springproj.model.TypeMenu;
import tn.esprit.tic.springproj.repository.MenuRepository;
import tn.esprit.tic.springproj.service.CrudService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MenuServiceImpl implements CrudService<Menu> {

    @Autowired
    private MenuRepository repository;

    @Override
    public List<Menu> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Menu> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Menu save(Menu entity) {
        return repository.save(entity);
    }

    @Override
    public Menu update(Long id, Menu entity) {
        if (repository.existsById(id)) {
            entity.setIdMenu(id); // Assuming Menu has a setIdMenu method
            return repository.save(entity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<String> nomMenuParTypeMenuOrdonneParPrixTotal(TypeMenu typeMenu) {
        return repository.findMenuNamesByTypeOrderedByPrixTotal(typeMenu.name());
    }

    public List<Menu> listeMenuSelonTypeMenuEtprixComposantsSuperieurAUnMontant(TypeMenu typeMenu, Float prixTotal) {
        return repository.findByTypeMenuAndPrixTotalGreaterThan(typeMenu, prixTotal);
    }

    public Menu ajoutComposantsEtMiseAjourPrixMenu(Set<Composant> composants, Long idMenu) {
        // Retrieve the menu by its ID
        Menu menu = repository.findById(idMenu).orElseThrow(() -> new IllegalArgumentException("Menu not found"));

        // Add the components to the menu
        menu.getComposants().addAll(composants);

        // Calculate the total price of the components
        float totalPrixComposants = composants.stream()
                .map(Composant::getPrix)
                .reduce(0f, Float::sum);

        // Ensure the total price does not exceed 20 dinars
        if (totalPrixComposants > 20) {
            throw new IllegalArgumentException("The total price of the menu cannot exceed 20 dinars");
        }

        // Update the menu's price
        menu.setPrixTotal(totalPrixComposants);

        // Save the updated menu
        return repository.save(menu);
    }


}