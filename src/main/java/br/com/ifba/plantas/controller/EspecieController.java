package br.com.ifba.plantas.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifba.infrastructure.mapper.ObjectMapperUtil;
import br.com.ifba.plantas.dto.especie.EspecieGetResponseDTO;
import br.com.ifba.plantas.dto.especie.EspeciePostRequestDTO;
import br.com.ifba.plantas.entity.Especie;
import br.com.ifba.plantas.service.EspecieService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/especies")
public class EspecieController {

    private final EspecieService service;
    private final ObjectMapperUtil mapper;

    public EspecieController(EspecieService service, ObjectMapperUtil mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    // Pega todas as espécies (sem paginação, porque o front não suporta ainda)
    @GetMapping
    public ResponseEntity<List<EspecieGetResponseDTO>> findAll() {

        List<Especie> especies = service.findAll();

        // Converte cada espécie para o formato de resposta que o front espera
        List<EspecieGetResponseDTO> response = especies.stream()
                .map(e -> mapper.map(e, EspecieGetResponseDTO.class))
                .toList();

        return ResponseEntity.ok(response);
    }

    // Busca uma espécie específica pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<EspecieGetResponseDTO> findById(@PathVariable Long id) {
        Especie especie = service.findById(id);
        return ResponseEntity.ok(mapper.map(especie, EspecieGetResponseDTO.class));
    }

    // Cria uma nova espécie
    @PostMapping
    public ResponseEntity<EspecieGetResponseDTO> create(
            @RequestBody @Valid EspeciePostRequestDTO dto) {

        // Converte os dados recebidos para a entidade do banco
        Especie especie = mapper.map(dto, Especie.class);
        Especie salva = service.save(especie);

        // Retorna a espécie criada, convertida para o formato de resposta
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.map(salva, EspecieGetResponseDTO.class));
    }

    // Atualiza uma espécie existente
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Long id,
            @RequestBody @Valid EspeciePostRequestDTO dto) {

        Especie especie = mapper.map(dto, Especie.class);
        // Garante que estamos atualizando a espécie correta
        especie.setId(id);

        service.update(especie);
        // 204 No Content - deu certo mas não precisa retornar nada
        return ResponseEntity.noContent().build();
    }

    // Remove uma espécie
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        // 204 No Content - removeu com sucesso
        return ResponseEntity.noContent().build();
    }
}