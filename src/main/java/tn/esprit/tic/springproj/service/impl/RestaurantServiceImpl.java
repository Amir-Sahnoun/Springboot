package tn.esprit.tic.springproj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tic.springproj.model.Restaurant;
import tn.esprit.tic.springproj.repository.RestaurantRepository;
import tn.esprit.tic.springproj.service.CrudService;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements CrudService<Restaurant> {

    @Autowired
    private RestaurantRepository repository;

    @Override
    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Restaurant> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Restaurant save(Restaurant entity) {
        return repository.save(entity);
    }

    @Override
    public Restaurant update(Long id, Restaurant entity) {
        if (repository.existsById(id)) {
            entity.setIdRestaurant(id); // Assuming Restaurant has a setIdRestaurant method
            return repository.save(entity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
