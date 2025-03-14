package tn.esprit.tic.springproj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tic.springproj.model.Commande;
import tn.esprit.tic.springproj.repository.CommandeRepository;
import tn.esprit.tic.springproj.service.CrudService;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeServiceImpl implements CrudService<Commande> {

    @Autowired
    private CommandeRepository repository;

    @Override
    public List<Commande> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Commande> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Commande save(Commande entity) {
        return repository.save(entity);
    }

    @Override
    public Commande update(Long id, Commande entity) {
        if (repository.existsById(id)) {
            entity.setIdCommande(id); // Assuming Commande has a setIdCommande method
            return repository.save(entity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
