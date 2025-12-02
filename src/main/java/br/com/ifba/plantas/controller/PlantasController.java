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

import br.com.ifba.plantas.entity.Planta;
import br.com.ifba.plantas.service.PlantasService;

@RestController
@RequestMapping("/plantas")
public class PlantasController {

    private final PlantasService service;

    public PlantasController(PlantasService service) {
        this.service = service;
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Planta>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planta> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Planta> create(@RequestBody Planta planta) {
        Planta nova = service.save(planta);
        return ResponseEntity.status(HttpStatus.CREATED).body(nova);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody Planta planta) {
        service.update(planta);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
