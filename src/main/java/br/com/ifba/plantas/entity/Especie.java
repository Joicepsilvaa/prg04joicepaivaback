package br.com.ifba.plantas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCientifico;
    private String nomePopular;
    private String tipo;
    private String luz;
    private String frequenciaRega;

    public Especie() {}

    public Especie(Long id, String nomeCientifico, String nomePopular, String tipo,
                   String luz, String frequenciaRega) {
        this.id = id;
        this.nomeCientifico = nomeCientifico;
        this.nomePopular = nomePopular;
        this.tipo = tipo;
        this.luz = luz;
        this.frequenciaRega = frequenciaRega;
    }
}
