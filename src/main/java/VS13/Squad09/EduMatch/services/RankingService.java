package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.ranking.request.RankingCreateDTO;
import VS13.Squad09.EduMatch.dtos.ranking.response.RankingDTO;
import VS13.Squad09.EduMatch.dtos.ranking.response.RankingUsuarioDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.entities.Ranking;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.repositories.RankingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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


    public RankingDTO criar(RankingCreateDTO rankingCreateDTO) throws Exception{
        Ranking ranking = toEntity(rankingCreateDTO);
        ranking.setStatus(Status.ATIVO);
        return toDTO(rankingRepository.save(ranking));
    }

    public Page<RankingUsuarioDTO> listarPorRanking(String elo, Pageable page){
        Page<Ranking> rankingPage;
        if (elo != null){
            elo = elo.toUpperCase();
            rankingPage =  rankingRepository.findAllByTitulo(elo, page);
        } else {
            rankingPage = rankingRepository.findAll(page);
        }
        List<RankingUsuarioDTO> rankingUsuarioDTOList = rankingPage.getContent()
                .stream()
                .map(ranking -> objectMapper.convertValue(ranking, RankingUsuarioDTO.class))
                .collect(Collectors.toList());

        return new PageImpl<>(rankingUsuarioDTOList, page, rankingPage.getTotalElements());
    }

    public Ranking subirRanking(String nome, Usuario usuario) {
        Ranking ranking = rankingRepository.findByElo(nome);
        if(usuario.getPontuacao() >= ranking.getPontuacaoNecessaria()){
            return ranking;
        }
        return null;
    }


    public Ranking rankingInicial() {
        return rankingRepository.findByElo("FERRO");
    }



    //METODOS ADICIONAIS

    private RankingDTO toDTO(Object o){
        return objectMapper.convertValue(o, RankingDTO.class);
    }

    private Ranking toEntity(Object o){
        return objectMapper.convertValue(o, Ranking.class);
    }
    private Usuario userToEntity(UsuarioDTO usuarioDTO){
        return objectMapper.convertValue(usuarioDTO, Usuario.class);
    }

    private UsuarioDTO userToDTO(Object o){
        return objectMapper.convertValue(o, UsuarioDTO.class);
    }
}
