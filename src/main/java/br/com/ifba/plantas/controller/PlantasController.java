package br.com.ifba.plantas.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ifba.plantas.dto.planta.PlantaGetResponseDTO;
import br.com.ifba.plantas.dto.planta.PlantaPostRequestDTO;
import br.com.ifba.plantas.entity.Especie;
import br.com.ifba.plantas.entity.Planta;
import br.com.ifba.plantas.service.EspecieService;
import br.com.ifba.plantas.service.PlantasService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/plantas")
public class PlantasController {

    private final PlantasService plantasService;
    private final EspecieService especieService;

    public PlantasController(
            PlantasService plantasService,
            EspecieService especieService) {

        this.plantasService = plantasService;
        this.especieService = especieService;
    }

    // Pega todas as plantas
    @GetMapping
    public ResponseEntity<List<PlantaGetResponseDTO>> findAll() {
        List<Planta> plantas = plantasService.findAll();
        List<PlantaGetResponseDTO> response = plantas.stream()
                .map(this::convertToDTO)
                .toList();
        return ResponseEntity.ok(response);
    }

    // Busca uma planta específica pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<PlantaGetResponseDTO> findById(@PathVariable Long id) {
        Planta planta = plantasService.findById(id);
        return ResponseEntity.ok(convertToDTO(planta));
    }

    // Cria uma nova planta
    @PostMapping
    public ResponseEntity<PlantaGetResponseDTO> create(
            @RequestBody @Valid PlantaPostRequestDTO dto) {
        
        // Primeiro busca a espécie que a planta pertence
        Especie especie = especieService.findById(dto.getEspecieId());
        Planta planta = convertToEntity(dto);
        planta.setEspecie(especie); // Associa a espécie à planta
        
        Planta salva = plantasService.save(planta);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(salva));
    }

    // Atualiza uma planta existente
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable Long id,
            @RequestBody @Valid PlantaPostRequestDTO dto) {
        
        // Busca a espécie e cria a planta com o ID certo
        Especie especie = especieService.findById(dto.getEspecieId());
        Planta planta = convertToEntity(dto);
        planta.setId(id);
        planta.setEspecie(especie);
        
        plantasService.update(planta);
        return ResponseEntity.noContent().build();
    }

    // Remove uma planta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        plantasService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ----- Métodos auxiliares para converter entre DTO e entidade -----
    
    // Converte os dados que vieram do front (DTO) para a entidade do banco
    private Planta convertToEntity(PlantaPostRequestDTO dto) {
        Planta planta = new Planta();
        planta.setNomePlanta(dto.getNomePlanta());
        planta.setLocal(dto.getLocal());
        planta.setObservacoes(dto.getObservacoes());
        return planta;
    }
    
    // Converte a entidade do banco para o formato que o front espera (DTO)
    private PlantaGetResponseDTO convertToDTO(Planta planta) {
        PlantaGetResponseDTO dto = new PlantaGetResponseDTO();
        dto.setNomePlanta(planta.getNomePlanta());
        dto.setLocal(planta.getLocal());
        dto.setObservacoes(planta.getObservacoes());
        dto.setEspecieId(planta.getEspecie().getId()); // Só mandamos o ID da espécie
        return dto;
    }
}