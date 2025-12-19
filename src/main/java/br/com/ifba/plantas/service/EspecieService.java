package br.com.ifba.plantas.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ifba.infrastructure.exception.BusinessException;
import br.com.ifba.plantas.entity.Especie;
import br.com.ifba.plantas.repository.EspecieRepository;

@Service
public class EspecieService {

    private final EspecieRepository repository;

    public EspecieService(EspecieRepository repository) {
        this.repository = repository;
    }

    // Busca todas as espécies com paginação (útil para telas com muitos registros)
    public Page<Especie> findAll(Pageable pageable) {
        Page<Especie> especies = repository.findAll(pageable);

        // Se não tiver nada cadastrado, já avisa o usuário
        if (especies.isEmpty()) {
            throw new BusinessException("Nenhuma espécie cadastrada no sistema!");
        }

        return especies;
    }

    // Busca todas as espécies sem paginação (usado pelo front atual)
    public List<Especie> findAll() {
        List<Especie> especies = repository.findAll();

        if (especies.isEmpty()) {
            throw new BusinessException("Nenhuma espécie cadastrada no sistema!");
        }

        return especies;
    }

    // Procura uma espécie pelo ID, se não encontrar, lança erro
    public Especie findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException("Espécie não encontrada com o ID: " + id));
    }

    // Salva uma nova espécie no banco (inicia uma transação)
    @Transactional
    public Especie save(Especie especie) {
        return repository.save(especie);
    }

    // Atualiza uma espécie existente
    @Transactional
    public void update(Especie especie) {
        // Pra atualizar precisa ter um ID
        if (especie.getId() == null) {
            throw new BusinessException("ID da espécie é obrigatório para atualização!");
        }

        // Verifica se a espécie realmente existe antes de tentar atualizar
        if (!repository.existsById(especie.getId())) {
            throw new BusinessException("Não é possível atualizar: espécie não encontrada!");
        }

        repository.save(especie);
    }

    // Remove uma espécie pelo ID
    @Transactional
    public void delete(Long id) {
        // Só deleta se a espécie existir
        if (!repository.existsById(id)) {
            throw new BusinessException("Não é possível deletar: espécie não encontrada!");
        }

        repository.deleteById(id);
    }
}