package br.com.ifba.plantas.dto.planta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PlantaPostRequestDTO {

    @NotBlank(message = "O nome do usuário é obrigatório")
    private String nomeUsuario;

    @NotBlank(message = "O local é obrigatório")
    private String local;

    @NotBlank(message = "A data de aquisição é obrigatória")
    private String dataAquisicao;

    private String observacoes;

    @NotNull(message = "O ID da espécie é obrigatório")
    private Long especieId;
}