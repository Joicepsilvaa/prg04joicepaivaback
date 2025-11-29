package br.com.ifba.plantas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ifba.plantas.entity.Especie;
import br.com.ifba.plantas.repository.EspecieRepository;

@Service
public class EspecieService {
    
    private final EspecieRepository repository;

    public EspecieService(EspecieRepository repository) {
        this.repository = repository;
    }

    public List<Especie> findAll() {
        return repository.findAll();
    }

    public Especie save(Especie especie) {
        return repository.save(especie);
    }

    public Especie update(Especie especie) {
        return repository.update(especie);
    }

    public boolean delete(Long id) {
        return repository.delete(id);
    }

    public Especie findById(Long id) {
        return repository.findById(id);
    }
}
