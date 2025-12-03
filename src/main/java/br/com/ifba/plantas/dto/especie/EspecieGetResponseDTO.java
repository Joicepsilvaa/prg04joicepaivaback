package br.com.ifba.plantas.dto.especie;

import lombok.Data;

@Data
public class EspecieGetResponseDTO {
    private String nomeCientifico;
    private String nomePopular;
    private String tipo;
    private String luz;
    private String frequenciaRega;
}
