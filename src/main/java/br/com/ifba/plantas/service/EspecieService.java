package br.com.ifba.plantas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.plantas.entity.Especie;
import br.com.ifba.plantas.repository.EspecieRepository;

@Service
public class EspecieService {

    private final EspecieRepository repository;

    public EspecieService(EspecieRepository repository) {
        this.repository = repository;
    }

    // Listar todos
    public List<Especie> findAll() {
        List<Especie> especies = repository.findAll();

        if (especies.isEmpty()) {
            throw new BusinessException("Nenhuma espécie cadastrada no sistema!");
        }

        return especies;
    }

    // Buscar por id
    public Especie findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException("Espécie não encontrada com o ID: " + id));
    }

    // Salvar
    public Especie save(Especie especie) {

        if (especie.getNomeCientifico() == null || especie.getNomeCientifico().isBlank()) {
            throw new BusinessException("O nome científico é obrigatório!");
        }

        if (especie.getNomePopular() == null || especie.getNomePopular().isBlank()) {
            throw new BusinessException("O nome popular é obrigatório!");
        }

        return repository.save(especie);
    }

    // Atualizar
    public void update(Especie especie) {
        if (especie.getId() == null) {
            throw new BusinessException("ID da espécie é obrigatório para atualização!");
        }

        if (!repository.existsById(especie.getId())) {
            throw new BusinessException("Não é possível atualizar: espécie não encontrada!");
        }

        repository.save(especie);
    }

    // Deletar
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("Não é possível deletar: espécie não encontrada!");
        }

        repository.deleteById(id);
    }
}
