package br.com.ifba.plantas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.ifba.plantas.entity.Especie;
import br.com.ifba.plantas.service.EspecieService;

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
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Especie> create(@RequestBody Especie especie) {
        return ResponseEntity.ok(service.save(especie));
    }

    @PutMapping("/update")
    public ResponseEntity<Especie> update(@RequestBody Especie especie) {
        return ResponseEntity.ok(service.update(especie));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
