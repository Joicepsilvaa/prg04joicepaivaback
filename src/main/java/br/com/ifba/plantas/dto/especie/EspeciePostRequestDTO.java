package br.com.ifba.plantas.dto.especie;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EspeciePostRequestDTO {

    @NotBlank(message = "O nome científico é obrigatório")
    private String nomeCientifico;

    @NotBlank(message = "O nome popular é obrigatório")
    private String nomePopular;

    @NotBlank(message = "O tipo é obrigatório")
    private String tipo;

    @NotBlank(message = "A luz é obrigatória")
    private String luz;

    @NotBlank(message = "A frequência de rega é obrigatória")
    private String frequenciaRega;
}
