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
import br.com.ifba.plantas.dto.planta.PlantaGetResponseDTO;
import br.com.ifba.plantas.dto.planta.PlantaPostRequestDTO;
import br.com.ifba.plantas.entity.Planta;
import br.com.ifba.plantas.service.PlantasService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/plantas")
public class PlantasController {

    private final PlantasService service;
    private final ObjectMapperUtil mapper;

    public PlantasController(PlantasService service, ObjectMapperUtil mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/findall")
    public ResponseEntity<List<PlantaGetResponseDTO>> findAll() {
        List<Planta> plantas = service.findAll();
        List<PlantaGetResponseDTO> response =
                mapper.mapAll(plantas, PlantaGetResponseDTO.class);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantaGetResponseDTO> findById(@PathVariable Long id) {
        Planta planta = service.findById(id);
        PlantaGetResponseDTO dto =
                mapper.map(planta, PlantaGetResponseDTO.class);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/create")
    public ResponseEntity<PlantaGetResponseDTO> create(
            @RequestBody @Valid PlantaPostRequestDTO dto) {

        Planta planta = mapper.map(dto, Planta.class);
        Planta nova = service.save(planta);
        PlantaGetResponseDTO response =
                mapper.map(nova, PlantaGetResponseDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(
            @RequestBody @Valid PlantaPostRequestDTO dto) {

        Planta planta = mapper.map(dto, Planta.class);
        service.update(planta);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
