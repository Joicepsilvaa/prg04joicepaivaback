package br.com.ifba.plantas.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.plantas.entity.Planta;
import br.com.ifba.plantas.repository.PlantasRepository;

@Service
public class PlantasService {

    private final PlantasRepository repository;

    public PlantasService(PlantasRepository repository) {
        this.repository = repository;
    }

    public List<Planta> findAll() {
        List<Planta> plantas = repository.findAll();

        if (plantas.isEmpty()) {
            throw new BusinessException("Nenhuma planta cadastrada!");
        }

        return plantas;
    }

    public Planta findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException("Planta não encontrada!"));
    }

    @Transactional
    public Planta save(Planta planta) {
        if (planta.getEspecie() == null) {
            throw new BusinessException("Espécie é obrigatória!");
        }

        return repository.save(planta);
    }

@Transactional
public void update(Planta planta) {
    if (planta.getId() == null) {
        throw new BusinessException("ID da planta é obrigatório!");
    }

    if (!repository.existsById(planta.getId())) {
        throw new BusinessException("Planta não encontrada!");
    }

    repository.save(planta); // Só isso, igual ao Especie
}
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("Planta não encontrada!");
        }

        repository.deleteById(id);
    }
}
