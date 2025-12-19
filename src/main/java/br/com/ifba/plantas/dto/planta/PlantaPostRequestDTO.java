package br.com.ifba.plantas.dto.planta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlantaPostRequestDTO {

    @NotBlank(message = "O nome da planta é obrigatório")  
    private String nomePlanta; 

    @NotBlank(message = "O local é obrigatório")
    private String local;
    
    private String observacoes;

    @NotNull(message = "O ID da espécie é obrigatório")
    private Long especieId;
}