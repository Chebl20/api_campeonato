package com.api.championship.dto;

import java.time.LocalDateTime;

public class PartidaInfoDTO {
    private Long id;
    private LocalDateTime data;
    private String nomeTimeMandante;
    private String nomeTimeVisitante;

    public PartidaInfoDTO() {}

    public PartidaInfoDTO(Long id, LocalDateTime data, String nomeTimeMandante, String nomeTimeVisitante) {
        this.id = id;
        this.data = data;
        this.nomeTimeMandante = nomeTimeMandante;
        this.nomeTimeVisitante = nomeTimeVisitante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getNomeTimeMandante() {
        return nomeTimeMandante;
    }

    public void setNomeTimeMandante(String nomeTimeMandante) {
        this.nomeTimeMandante = nomeTimeMandante;
    }

    public String getNomeTimeVisitante() {
        return nomeTimeVisitante;
    }

    public void setNomeTimeVisitante(String nomeTimeVisitante) {
        this.nomeTimeVisitante = nomeTimeVisitante;
    }
}
