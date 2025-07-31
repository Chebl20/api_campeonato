package com.api.championship.service;

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
    
    public List<Time> listarTimesDoCampeonato(Long campeonatoId) {
        return timeRepository.findAllByCampeonatoId(campeonatoId);
    }
    
    public List<Campeonato> listarPartidasOcorridas(Long campeonatoId) {
        return campeonatoRepository.findPartidasOcorridas(campeonatoId);
    }
    
    public List<Campeonato> listarPartidasNaoOcorridas(Long campeonatoId) {
        return campeonatoRepository.findPartidasNaoOcorridas(campeonatoId);
    }
    
    public List<TimeClassificacao> getTabelaCampeonato(Long campeonatoId) {
        Campeonato campeonato = campeonatoRepository.findByIdWithTimes(campeonatoId);
        Map<Long, TimeClassificacao> classificacao = new HashMap<>();
        
        // Inicializa a classificação
        campeonato.getTimes().forEach(time -> {
            classificacao.put(time.getId(), new TimeClassificacao(time));
        });
        
        // Calcula os pontos
        campeonato.getPartidas().forEach(partida -> {
            if (partida.getResultado() != null && 
                partida.getResultado().getGolsTimeMandante() != null && 
                partida.getResultado().getGolsTimeVisitante() != null) {
                
                TimeClassificacao timeMandante = classificacao.get(partida.getTimeMandante().getId());
                TimeClassificacao timeVisitante = classificacao.get(partida.getTimeVisitante().getId());
                
                int golsMandante = partida.getResultado().getGolsTimeMandante();
                int golsVisitante = partida.getResultado().getGolsTimeVisitante();
                
                // Atualiza saldo de gols
                timeMandante.setSaldoGols(timeMandante.getSaldoGols() + golsMandante - golsVisitante);
                timeVisitante.setSaldoGols(timeVisitante.getSaldoGols() + golsVisitante - golsMandante);
                
                // Atualiza vitórias
                if (golsMandante > golsVisitante) {
                    timeMandante.setVitorias(timeMandante.getVitorias() + 1);
                } else if (golsVisitante > golsMandante) {
                    timeVisitante.setVitorias(timeVisitante.getVitorias() + 1);
                }
            }
        });
        
        // Ordena por vitórias e saldo de gols
        return classificacao.values().stream()
                .sorted(Comparator.comparing(TimeClassificacao::getVitorias).reversed()
                        .thenComparing(TimeClassificacao::getSaldoGols).reversed())
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
