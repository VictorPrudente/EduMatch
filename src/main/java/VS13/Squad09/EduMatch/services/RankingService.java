package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.response.RankingDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.entities.Ranking;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Elo;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
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

    public Page<RankingDTO> listarPorRanking(String elo, Pageable page){
        if (elo != null){
            elo = elo.toUpperCase();
            return rankingRepository.findAllByTitulo(elo, page)
                    .map(ranking -> objectMapper.convertValue(ranking, RankingDTO.class));
        }
        return rankingRepository.findAll(page)
                .map(ranking -> objectMapper.convertValue(ranking, RankingDTO.class));
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

    private Usuario userToEntity(UsuarioDTO usuarioDTO){
        return objectMapper.convertValue(usuarioDTO, Usuario.class);
    }

    private UsuarioDTO userToDTO(Object o){
        return objectMapper.convertValue(o, UsuarioDTO.class);
    }
}
