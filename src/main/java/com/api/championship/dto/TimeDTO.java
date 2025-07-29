package com.api.championship.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TimeDTO {
    private Long id;
    
    @NotBlank(message = "O nome do time é obrigatório")
    private String nome;
    
    @NotBlank(message = "A cidade do time é obrigatória")
    private String cidade;
    
    private Long estadioSedeId;
}
