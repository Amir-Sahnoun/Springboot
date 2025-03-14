package tn.esprit.tic.springproj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tic.springproj.model.Client;
import tn.esprit.tic.springproj.repository.ClientRepository;
import tn.esprit.tic.springproj.service.CrudService;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements CrudService<Client> {

    @Autowired
    private ClientRepository repository;

    @Override
    public List<Client> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Client> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Client save(Client entity) {
        return repository.save(entity);
    }

    @Override
    public Client update(Long id, Client entity) {
        if (repository.existsById(id)) {
            entity.setIdClient(id); // Assuming Client has a setIdClient method
            return repository.save(entity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}