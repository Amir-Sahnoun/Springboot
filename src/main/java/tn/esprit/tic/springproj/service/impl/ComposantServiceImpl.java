package tn.esprit.tic.springproj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tic.springproj.model.Composant;
import tn.esprit.tic.springproj.repository.ComposantRepository;
import tn.esprit.tic.springproj.service.CrudService;

import java.util.List;
import java.util.Optional;

@Service
public class ComposantServiceImpl implements CrudService<Composant> {

    @Autowired
    private ComposantRepository repository;

    @Override
    public List<Composant> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Composant> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Composant save(Composant entity) {
        return repository.save(entity);
    }

    @Override
    public Composant update(Long id, Composant entity) {
        if (repository.existsById(id)) {
            entity.setIdComposant(id); // Assuming Composant has a setIdComposant method
            return repository.save(entity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
