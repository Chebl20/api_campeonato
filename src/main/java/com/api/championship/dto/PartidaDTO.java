package com.api.championship.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PartidaDTO {
    private Long id;
    private LocalDateTime data;
    private Long timeMandanteId;
    private Long timeVisitanteId;
    private Long estadioId;
    private Long campeonatoId;
    private ResultadoDTO resultado;
}
