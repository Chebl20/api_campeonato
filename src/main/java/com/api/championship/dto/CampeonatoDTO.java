package com.api.championship.dto;

import lombok.Data;
import java.time.Year;

@Data
public class CampeonatoDTO {
    private Long id;
    private String nome;
    private Integer ano;
}
