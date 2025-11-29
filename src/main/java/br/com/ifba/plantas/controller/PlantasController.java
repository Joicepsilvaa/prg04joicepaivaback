/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.plantas.controller;

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

/**
 *
 * @author Joice
 */

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
        return ResponseEntity.ok(service.save(planta));
    }

    @PutMapping("/update")
    public ResponseEntity<Planta> update(@RequestBody Planta planta) {
        return ResponseEntity.ok(service.update(planta));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
