package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.request.RankingCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.RankingDTO;
import VS13.Squad09.EduMatch.entities.Ranking;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
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


    //OK
    public RankingDTO create(RankingCreateDTO rankingCreateDTO) throws Exception{
        Ranking ranking = toEntity(rankingCreateDTO);
        ranking.setStatus(Status.ATIVO);
        return toDTO(rankingRepository.save(ranking));
    }

    //OK
    public RankingDTO update(String elo, RankingCreateDTO rankingCreateDTO) throws NaoEncontradoException {

        RankingDTO rankingDTO = getRanking(elo);

        Ranking rankingAtualizado = toEntity(rankingCreateDTO);
        rankingAtualizado.setId(rankingDTO.getId());
        rankingAtualizado.setStatus(Status.ATIVO);

       return toDTO(rankingRepository.save(rankingAtualizado));
    }

    //OK
    public List<RankingDTO> findAll(Integer idRanking) {
        if (idRanking == null){
            return rankingRepository.findRankings();
        } else {
            return rankingRepository.findRanking(idRanking);
        }
    }

    public List<RankingDTO> listarPorElo(){
        return rankingRepository.findElo(null).stream()
                .peek(rankingDTO -> rankingDTO.setUsuarios(rankingRepository.getUsers(rankingDTO.getId())))
                .peek(rankingDTO -> rankingDTO.getUsuarios().forEach(usuarioMinDTO -> usuarioMinDTO.setPosicao(rankingRepository.posicaoJogador(usuarioMinDTO.getIdUsuario()))))
                .toList();
    }

    public Ranking novoRanking(String elo){
        return rankingRepository.findByTitulo(elo);
    }

    //METODOS ADICIONAIS

    public RankingDTO getRanking(String elo) throws NaoEncontradoException {
        return rankingRepository.findElo(elo).stream()
                .findFirst()
                .orElseThrow(() -> new NaoEncontradoException("Nenhum ranking encontrado com este ID."));
    }

    private RankingDTO toDTO(Object o){
        return objectMapper.convertValue(o, RankingDTO.class);
    }

    private Ranking toEntity(Object o){
        return objectMapper.convertValue(o, Ranking.class);
    }
}
