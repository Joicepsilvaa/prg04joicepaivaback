package br.com.ifba.plantas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.plantas.entity.Planta;
import br.com.ifba.plantas.repository.PlantasRepository;

@Service
public class PlantasService {

    private final PlantasRepository repository;

    public PlantasService(PlantasRepository repository) {
        this.repository = repository;
    }

    // Listar todas
    public List<Planta> findAll() {
        List<Planta> plantas = repository.findAll();

        if (plantas.isEmpty()) {
            throw new BusinessException("Nenhuma planta cadastrada no sistema!");
        }

        return plantas;
    }

    // Buscar por id
    public Planta findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException("Planta não encontrada com o ID: " + id));
    }

    // Salvar
    public Planta save(Planta planta) {

        if (planta.getNomeUsuario() == null || planta.getNomeUsuario().isBlank()) {
            throw new BusinessException("O nome do usuário é obrigatório!");
        }

        if (planta.getLocal() == null || planta.getLocal().isBlank()) {
            throw new BusinessException("O local da planta é obrigatório!");
        }

        if (planta.getEspecieId() == null) {
            throw new BusinessException("O ID da espécie é obrigatório!");
        }

        return repository.save(planta);
    }

    // Atualizar
    public void update(Planta planta) {
        if (planta.getId() == null) {
            throw new BusinessException("O ID da planta é obrigatório para atualização!");
        }

        if (!repository.existsById(planta.getId())) {
            throw new BusinessException("Não é possível atualizar: planta não encontrada!");
        }

        repository.save(planta);
    }

    // Deletar
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("Não é possível deletar: planta não encontrada!");
        }

        repository.deleteById(id);
    }
}