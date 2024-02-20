package VS13.Squad09.EduMatch;


import VS13.Squad09.EduMatch.dtos.request.RankingCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.RankingDTO;
import VS13.Squad09.EduMatch.entities.Ranking;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import VS13.Squad09.EduMatch.repositories.RankingRepository;
import VS13.Squad09.EduMatch.services.RankingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("RankingService - Testes")
public class RankingServiceTest {


    @Mock
    RankingRepository rankingRepository;

    @Mock
    ObjectMapper objectMapper;

    @Spy
    @InjectMocks
    RankingService rankingService;

    RankingCreateDTO rankingCreateDTO;
    RankingDTO rankingDTO;
    Ranking ranking;

    @BeforeEach
    public void setUp(){
        this.rankingCreateDTO = mockRankingCreateDTO();
        this.rankingDTO = mockRankingDTO();
        this.ranking = mockRanking();
    }


    @Test
    @DisplayName("Deveria criar um ranking e setar o status dele como ativ")
    public void createTest() throws Exception {

        //WHEN
        when(toEntity(rankingCreateDTO))
                .thenReturn(ranking)
                .then(invocation -> {
                    ranking.setStatus(Status.ATIVO);
                    return ranking;
                });

        when(rankingRepository.save(any()))
                .thenReturn(ranking);

        when(toDTO(ranking))
                .thenReturn(rankingDTO);

        //THEN
        RankingDTO rankingDTOCriada = rankingService.create(rankingCreateDTO);

        assertNotNull(rankingDTOCriada);
        assertNotNull(ranking);
        assertEquals(ranking.getStatus(), Status.ATIVO);
        assertEquals(rankingDTOCriada, rankingDTO);

        verify(rankingRepository, times(1)).save(any());

    }

    @Test
    @DisplayName("Deveria atualizar uma ranking")
    public void updateTest() throws NaoEncontradoException {

        //GIVEN
        String elo = anyString();
        Ranking novoRanking = mockRankingAtualizado();
        List<RankingDTO> rankingDTOS = new ArrayList<>();
        rankingDTOS.add(rankingDTO);

        //WHEN
        when(rankingRepository.findElo(elo))
                .thenReturn(rankingDTOS);

        when(toEntity(rankingCreateDTO))
                .thenReturn(novoRanking)
                .then(invocationOnMock -> {
                    novoRanking.setId(rankingDTO.getId());
                    return novoRanking;
                });

        when(rankingRepository.save(novoRanking))
                .thenReturn(novoRanking);

        when(toDTO(novoRanking))
                .thenReturn(rankingDTO);

        //THEN
        RankingDTO rankingDTOAtualizada = rankingService.update(elo, rankingCreateDTO);

        assertNotNull(rankingCreateDTO);
        assertNotNull(rankingDTOAtualizada);
        assertNotNull(novoRanking);

        assertEquals(novoRanking.getId(), ranking.getId());

        verify(rankingRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Deve listar um ranking quando o ID estiver presente")
    public void findAllWithIdTest() {

        //GIVEN
        Integer idRanking = anyInt();
        List<RankingDTO> mockRankings = Arrays.asList(rankingDTO);

        //WHEN
        when(rankingRepository.findRanking(idRanking))
                .thenReturn(mockRankings);

        //THEN
        List<RankingDTO> rankings = rankingService.findAll(anyInt());

        assertNotNull(rankings);
        assertEquals(mockRankings, rankings);
        verify(rankingRepository, times(1)).findRanking(idRanking);

    }

    @Test
    @DisplayName("Deve listar todos os rankings quando o ID não estiver presente")
    public void findAllWithoutIdTest() {

        //GIVEN
        Integer idRanking = null;
        List<RankingDTO> mockRankings = Arrays.asList(rankingDTO, rankingDTO, rankingDTO);

        //WHEN
        when(rankingRepository.findRankings())
                .thenReturn(mockRankings);

        //THEN
        List<RankingDTO> rankings = rankingService.findAll(idRanking);

        assertNotNull(rankings);
        assertEquals(mockRankings, rankings);
        verify(rankingRepository, times(1)).findRankings();

    }


    @Test
    @DisplayName("Deve listar o primeiro ranking disponível")
    public void novoRankingTest(){

        //GIVEN
        String elo = anyString();

        //WHEN
        when(rankingRepository.findByTitulo(elo))
                .thenReturn(ranking);

        //THEN
        Ranking ranking = rankingService.novoRanking(elo);

        assertNotNull(elo);
        assertNotNull(ranking);
        verify(rankingRepository, times(1)).findByTitulo(elo);
    }


    @Test
    @DisplayName("Deve listar um ranking pelo seu nome")
    public void getRankingTest() throws NaoEncontradoException {

        //GIVEN
        String elo = anyString();
        List<RankingDTO> rankings = new ArrayList<>();
        rankings.add(rankingDTO);

        //WHEN
        when(rankingRepository.findElo(elo))
                .thenReturn(rankings)
                .then(invocationOnMock -> {
                    rankings.stream().findFirst().get();
                    return rankingDTO;
                });

        //THEN
        RankingDTO rankingDTO = rankingService.getRanking(elo);

        assertNotNull(rankingDTO);
        assertNotNull(elo);

    }

    @Test
    @DisplayName("Deveria listar todos os jogadores ordenando-os por elo.")
    public void listarPorEloTest(){

        //GIVEN
        List<RankingDTO> rankings = Arrays.asList(rankingDTO, rankingDTO, rankingDTO);


        //WHEN
        when(rankingRepository.findElo(null))
                .thenReturn(rankings);

        //THEN
        List<RankingDTO> rankingsTest = rankingService.listarPorElo();

        assertEquals(rankingsTest, rankings);

    }

    private RankingCreateDTO mockRankingCreateDTO(){
        return new RankingCreateDTO(
                "Bronze",
                "https://raw.githubusercontent.com/VictorPrudente/EduMatch/spring/migration/src/main/resources/images/portugues_facil.png",
                "Descrição teste",
                200
        );
    }

    private RankingDTO mockRankingDTO(){
       return new RankingDTO(
               1,
               "Bronze",
               "https://raw.githubusercontent.com/VictorPrudente/EduMatch/spring/migration/src/main/resources/images/portugues_facil.png",
               "Descrição teste",
               200,
               Status.ATIVO);
    }

    private Ranking mockRanking(){
        return new Ranking(
                1,
                "Bronze",
                "https://raw.githubusercontent.com/VictorPrudente/EduMatch/spring/migration/src/main/resources/images/portugues_facil.png",
                "Descrição teste",
                200,
                Status.ATIVO,
                new ArrayList<>(List.of(new Usuario())));
    }

    private Ranking mockRankingAtualizado(){
        return new Ranking(
                1,
                "Bronze",
                "https://raw.githubusercontent.com/VictorPrudente/EduMatch/spring/migration/src/main/resources/images/portugues_facil.png",
                "Descrição teste",
                200,
                Status.ATIVO,
                new ArrayList<>(List.of(new Usuario())));
    }


    private Ranking toEntity(Object o){
        return objectMapper.convertValue(o, Ranking.class);
    }

    private RankingDTO toDTO(Object o){
        return objectMapper.convertValue(o, RankingDTO.class);
    }
}
