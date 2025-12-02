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

import br.com.ifba.plantas.entity.Especie;
import br.com.ifba.plantas.service.EspecieService;

@RestController
@RequestMapping("/especies")
public class EspecieController {

    private final EspecieService service;

    public EspecieController(EspecieService service) {
        this.service = service;
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Especie>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especie> findById(@PathVariable Long id) {
        Especie especie = service.findById(id);
        return ResponseEntity.ok(especie);
    }

    @PostMapping("/create")
    public ResponseEntity<Especie> create(@RequestBody Especie especie) {
        Especie nova = service.save(especie);
        return ResponseEntity.status(HttpStatus.CREATED).body(nova);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody Especie especie) {
        service.update(especie);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
