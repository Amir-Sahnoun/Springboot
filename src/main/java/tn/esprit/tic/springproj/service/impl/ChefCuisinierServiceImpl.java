package tn.esprit.tic.springproj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tic.springproj.model.ChefCuisinier;
import tn.esprit.tic.springproj.model.TypeChef;
import tn.esprit.tic.springproj.repository.ChefCuisinierRepository;
import tn.esprit.tic.springproj.service.CrudService;

import java.util.List;
import java.util.Optional;

@Service
public class ChefCuisinierServiceImpl implements CrudService<ChefCuisinier> {

    @Autowired
    private ChefCuisinierRepository repository;

    @Override
    public List<ChefCuisinier> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<ChefCuisinier> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public ChefCuisinier save(ChefCuisinier entity) {
        return repository.save(entity);
    }

    @Override
    public ChefCuisinier update(Long id, ChefCuisinier entity) {
        if (repository.existsById(id)) {
            entity.setIdChefCuisinier(id); // Assuming ChefCuisinier has a setIdChefCuisinier method
            return repository.save(entity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<ChefCuisinier> listChefCuisinierByTypeChefAndRestaurant(TypeChef typeChef, String nomRestaurant) {
        return repository.findByTypeChefAndRestaurant(typeChef, nomRestaurant);
    }
}