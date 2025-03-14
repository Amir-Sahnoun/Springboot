package tn.esprit.tic.springproj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tic.springproj.model.ChainRestaurant;
import tn.esprit.tic.springproj.repository.ChainRestaurantRepository;
import tn.esprit.tic.springproj.service.CrudService;

import java.util.List;
import java.util.Optional;

@Service
public class ChainRestaurantServiceImpl implements CrudService<ChainRestaurant> {

    @Autowired
    private ChainRestaurantRepository repository;

    @Override
    public List<ChainRestaurant> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<ChainRestaurant> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public ChainRestaurant save(ChainRestaurant entity) {
        return repository.save(entity);
    }

    @Override
    public ChainRestaurant update(Long id, ChainRestaurant entity) {
        if (repository.existsById(id)) {
            entity.setIdChainRestaurant(id); // Assuming ChainRestaurant has a setId method
            return repository.save(entity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
