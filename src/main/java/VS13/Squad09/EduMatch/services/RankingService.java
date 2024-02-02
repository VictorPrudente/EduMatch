package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.response.RankingDTO;
import VS13.Squad09.EduMatch.entities.enums.Elo;
import VS13.Squad09.EduMatch.repositories.RankingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RankingService {

    private final RankingRepository rankingRepository;
    private final ObjectMapper objectMapper;



    public List<RankingDTO> listarTodos() throws Exception {
        log.debug("Listando Classificacaos...");
        return rankingRepository.findAll().stream().map(classificacao ->
                        objectMapper.convertValue(classificacao, RankingDTO.class))
                .collect(Collectors.toList());
    }

    public Page<RankingDTO> listarPorRanking(Elo elo, Pageable page){
        return rankingRepository.findByEloOrderByUsuariosDesc(elo, page)
                .map(ranking -> objectMapper.convertValue(ranking, RankingDTO.class));
    }

}
