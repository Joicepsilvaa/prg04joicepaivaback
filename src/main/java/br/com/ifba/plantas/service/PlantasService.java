package br.com.ifba.plantas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ifba.plantas.entity.Planta;
import br.com.ifba.plantas.repository.PlantasRepository;

@Service
public class PlantasService {

    private final PlantasRepository repository;

    public PlantasService(PlantasRepository repository) {
        this.repository = repository;
    }

    public List<Planta> findAll() {
        return repository.findAll();
    }

    public Planta findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Planta save(Planta planta) {
        return repository.save(planta);
    }

    public void update(Planta planta) {
        repository.save(planta);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
