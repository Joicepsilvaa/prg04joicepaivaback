package br.com.ifba.plantas.dto.planta;

import lombok.Data;

@Data
public class PlantaPostRequestDTO {
    private String nomeUsuario;
    private String local;
    private String dataAquisicao;
    private String observacoes;
    private Long especieId;
}
