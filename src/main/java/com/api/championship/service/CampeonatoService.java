package com.api.championship.service;

import com.api.championship.dto.TimeClassificacaoDTO;
import com.api.championship.dto.TimeInfoDTO;
import com.api.championship.dto.PartidaInfoDTO;
import com.api.championship.model.Campeonato;
import com.api.championship.model.Time;
import com.api.championship.repository.CampeonatoRepository;
import com.api.championship.repository.TimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampeonatoService {
    
    private final CampeonatoRepository campeonatoRepository;
    private final TimeRepository timeRepository;
    
    public List<TimeInfoDTO> listarTimesEssenciais(Long campeonatoId) {
        return campeonatoRepository.findTimesInfoByCampeonatoId(campeonatoId);
    }

    // Método antigo mantido para compatibilidade, se necessário
    public List<TimeInfoDTO> listarTimesDoCampeonato(Long campeonatoId) {
        return timeRepository.findAllByCampeonatoId(campeonatoId);
    }
    
    public List<PartidaInfoDTO> listarPartidasOcorridas(Long campeonatoId) {
        return campeonatoRepository.findPartidasOcorridasInfo(campeonatoId);
    }
    
    public List<PartidaInfoDTO> listarPartidasNaoOcorridas(Long campeonatoId) {
        return campeonatoRepository.findPartidasNaoOcorridasInfo(campeonatoId);
    }

    public List<TimeClassificacaoDTO> getTabelaCampeonato(Long campeonatoId) {
        Campeonato campeonato = campeonatoRepository.findByIdWithTimes(campeonatoId);
        Map<Long, TimeClassificacaoDTO> classificacao = new HashMap<>();

        // Inicializa
        campeonato.getTimes().forEach(time -> {
            classificacao.put(time.getId(), new TimeClassificacaoDTO(
                    time.getId(), time.getNome(), 0, 0
            ));
        });

        // Calcula
        campeonato.getPartidas().forEach(partida -> {
            if (partida.getResultado() != null &&
                    partida.getResultado().getGolsTimeMandante() != null &&
                    partida.getResultado().getGolsTimeVisitante() != null) {

                TimeClassificacaoDTO mandante = classificacao.get(partida.getTimeMandante().getId());
                TimeClassificacaoDTO visitante = classificacao.get(partida.getTimeVisitante().getId());

                int golsMandante = partida.getResultado().getGolsTimeMandante();
                int golsVisitante = partida.getResultado().getGolsTimeVisitante();

                mandante = new TimeClassificacaoDTO(
                        mandante.getId(), mandante.getNome(),
                        mandante.getVitorias() + (golsMandante > golsVisitante ? 1 : 0),
                        mandante.getSaldoGols() + (golsMandante - golsVisitante)
                );

                visitante = new TimeClassificacaoDTO(
                        visitante.getId(), visitante.getNome(),
                        visitante.getVitorias() + (golsVisitante > golsMandante ? 1 : 0),
                        visitante.getSaldoGols() + (golsVisitante - golsMandante)
                );

                classificacao.put(mandante.getId(), mandante);
                classificacao.put(visitante.getId(), visitante);
            }
        });

        // Ordena
        return classificacao.values().stream()
                .sorted(Comparator.comparing(TimeClassificacaoDTO::getVitorias).reversed()
                        .thenComparing(TimeClassificacaoDTO::getSaldoGols).reversed())
                .collect(Collectors.toList());
    }


    public static class TimeClassificacao {
        private final Time time;
        private int vitorias;
        private int saldoGols;
        
        public TimeClassificacao(Time time) {
            this.time = time;
            this.vitorias = 0;
            this.saldoGols = 0;
        }
        
        public Time getTime() {
            return time;
        }
        
        public int getVitorias() {
            return vitorias;
        }
        
        public void setVitorias(int vitorias) {
            this.vitorias = vitorias;
        }
        
        public int getSaldoGols() {
            return saldoGols;
        }
        
        public void setSaldoGols(int saldoGols) {
            this.saldoGols = saldoGols;
        }
    }
}
