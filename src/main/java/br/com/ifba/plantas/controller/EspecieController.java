package br.com.ifba.plantas.controller;

import java.util.List;

import org.springframework.data.web.PageableDefault;
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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RestController
@RequestMapping("/especies")
public class EspecieController {

    private final EspecieService service;
    private final ObjectMapperUtil mapper;

    public EspecieController(EspecieService service, ObjectMapperUtil mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/findall")
    public ResponseEntity<Page<EspecieGetResponseDTO>> findAll(
            @PageableDefault(page = 0, size = 5) Pageable pageable) {

        Page<Especie> especies = service.findAll(pageable);

        Page<EspecieGetResponseDTO> response =
                especies.map(especie ->
                        mapper.map(especie, EspecieGetResponseDTO.class));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecieGetResponseDTO> findById(@PathVariable Long id) {
        Especie especie = service.findById(id);
        EspecieGetResponseDTO dto =
                mapper.map(especie, EspecieGetResponseDTO.class);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/create")
    public ResponseEntity<EspecieGetResponseDTO> create(
            @RequestBody @Valid EspeciePostRequestDTO dto) {

        Especie especie = mapper.map(dto, Especie.class);
        Especie nova = service.save(especie);
        EspecieGetResponseDTO response =
                mapper.map(nova, EspecieGetResponseDTO.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(
            @RequestBody @Valid EspeciePostRequestDTO dto) {

        Especie especie = mapper.map(dto, Especie.class);
        service.update(especie);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
