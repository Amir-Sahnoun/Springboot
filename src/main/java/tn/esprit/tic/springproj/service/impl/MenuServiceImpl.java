package tn.esprit.tic.springproj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.tic.springproj.model.Menu;
import tn.esprit.tic.springproj.repository.MenuRepository;
import tn.esprit.tic.springproj.service.CrudService;

import java.util.List;
import java.util.Optional;

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
}