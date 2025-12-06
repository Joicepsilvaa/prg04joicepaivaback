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
            throw new BusinessException("Nenhuma planta cadastrada no sistema!");
        }

        return plantas;
    }

    public Planta findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException("Planta não encontrada com o ID: " + id));
    }

    @Transactional
    public Planta save(Planta planta) {
        return repository.save(planta);
    }

    @Transactional
    public void update(Planta planta) {
        if (planta.getId() == null) {
            throw new BusinessException("O ID da planta é obrigatório para atualização!");
        }

        if (!repository.existsById(planta.getId())) {
            throw new BusinessException("Não é possível atualizar: planta não encontrada!");
        }

        repository.save(planta);
    }

    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("Não é possível deletar: planta não encontrada!");
        }

        repository.deleteById(id);
    }
}
