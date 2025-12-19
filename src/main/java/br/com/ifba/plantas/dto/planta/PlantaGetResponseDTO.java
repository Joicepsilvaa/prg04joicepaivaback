package br.com.ifba.plantas.dto.planta;

import lombok.Data;

@Data
public class PlantaGetResponseDTO {
    private String nomePlanta; 
    private String local;
    private String observacoes;
    private Long especieId;
}