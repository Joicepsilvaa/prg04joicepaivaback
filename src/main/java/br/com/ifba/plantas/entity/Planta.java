package br.com.ifba.plantas.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Planta {

    private Long id;
    private String nomeUsuario;
    private String local;
    private String dataAquisicao;
    private String observacoes;
    private Long especieId;

    public Planta() {}

    public Planta(Long id, String nomeUsuario, String local, String dataAquisicao,
                  String observacoes, Long especieId) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.local = local;
        this.dataAquisicao = dataAquisicao;
        this.observacoes = observacoes;
        this.especieId = especieId;
    }

}
