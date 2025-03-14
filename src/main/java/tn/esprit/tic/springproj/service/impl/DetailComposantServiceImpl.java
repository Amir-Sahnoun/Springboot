package tn.esprit.tic.springproj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tic.springproj.model.DetailComposant;
import tn.esprit.tic.springproj.repository.DetailComposantRepository;
import tn.esprit.tic.springproj.service.CrudService;

import java.util.List;
import java.util.Optional;

@Service
public class DetailComposantServiceImpl implements CrudService<DetailComposant> {

    @Autowired
    private DetailComposantRepository repository;

    @Override
    public List<DetailComposant> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<DetailComposant> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public DetailComposant save(DetailComposant entity) {
        return repository.save(entity);
    }

    @Override
    public DetailComposant update(Long id, DetailComposant entity) {
        if (repository.existsById(id)) {
            entity.setIdDetailComposant(id); // Assuming DetailComposant has a setIdDetailComposant method
            return repository.save(entity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}