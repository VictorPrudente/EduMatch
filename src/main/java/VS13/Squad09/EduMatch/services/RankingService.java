package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.ranking.request.RankingCreateDTO;
import VS13.Squad09.EduMatch.dtos.ranking.response.RankingDTO;
import VS13.Squad09.EduMatch.dtos.ranking.response.RankingUsuarioDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.entities.Ranking;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Elo;
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

import java.util.ArrayList;
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

    public RankingDTO atualizar(Integer id, RankingCreateDTO rankingCreateDTO) throws NaoEncontradoException {

        Ranking ranking = getRanking(id);

        Ranking rankingAtualizado = toEntity(rankingCreateDTO);
        rankingAtualizado.setId(ranking.getId());
        rankingAtualizado.setStatus(Status.ATIVO);

       return toDTO(rankingRepository.save(rankingAtualizado));
    }

    public List<RankingDTO> listarRankings(String elo) throws Exception{
        List<RankingDTO> rankings = new ArrayList<>();
        if (elo != null) {
            elo = elo.toUpperCase();
            rankings.add(toDTO(rankingRepository.findByElo(elo)));
        } else {
            rankings.addAll(rankingRepository.findElos().stream()
                    .map(this::toDTO).toList());
        }
        return rankings;
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

    public Ranking subirRanking(Usuario usuario) {

        int eloAtual = usuario.getElo().ordinal();
        Integer proximoelo = eloAtual + 1;

        Ranking rankingAtual = usuario.getRanking();

        if (eloAtual < Elo.values().length) {
            String elo = Elo.valueOf(proximoelo).name();
            Ranking novoRanking = rankingRepository.findByElo(elo);
            if (usuario.getPontuacao() >= novoRanking.getPontuacaoNecessaria()) {
                return novoRanking;
            }
        }
        return rankingAtual;
    }

    public Ranking rankingInicial() {
        return rankingRepository.findByElo("FERRO");
    }


    //METODOS ADICIONAIS

    private Ranking getRanking(Integer id) throws NaoEncontradoException {
        return rankingRepository.findById(id).stream()
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
