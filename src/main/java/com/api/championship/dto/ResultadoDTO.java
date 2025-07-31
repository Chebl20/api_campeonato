package com.api.championship.dto;

import lombok.Data;

@Data
public class ResultadoDTO {
    private Long id;
    private Long partidaId;
    private Integer golsTimeMandante;
    private Integer golsTimeVisitante;
}
