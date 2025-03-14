package tn.esprit.tic.springproj.service;

import java.util.List;

import java.util.List;
import java.util.Optional;

// Generic CRUD interface
public interface CrudService<T> {
    List<T> getAll();
    Optional<T> getById(Long id);
    T save(T entity);
    T update(Long id, T entity);
    void delete(Long id);
}
