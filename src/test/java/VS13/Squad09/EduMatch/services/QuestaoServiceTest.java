//package VS13.Squad09.EduMatch.services;
//
//
//import VS13.Squad09.EduMatch.dtos.request.QuestaoCreateDTO;
//import VS13.Squad09.EduMatch.dtos.response.QuestaoDTO;
//import VS13.Squad09.EduMatch.entities.Opcao;
//import VS13.Squad09.EduMatch.entities.Prova;
//import VS13.Squad09.EduMatch.entities.Questao;
//import VS13.Squad09.EduMatch.entities.enums.Dificuldade;
//import VS13.Squad09.EduMatch.entities.enums.Status;
//import VS13.Squad09.EduMatch.entities.enums.Trilha;
//import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
//import VS13.Squad09.EduMatch.repositories.QuestaoRepository;
//import VS13.Squad09.EduMatch.services.QuestaoService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@DisplayName("QuestaoService - Testes")
//public class QuestaoServiceTest {
//
//    @Mock
//    QuestaoRepository questaoRepository;
//    @Mock
//    ObjectMapper objectMapper;
//
//    @Spy
//    @InjectMocks
//    QuestaoService questaoService;
//
//    private QuestaoCreateDTO questaoCreateDTO;
//    private QuestaoDTO questaoDTO;
//    private Questao questao;
//
//    @BeforeEach
//    public void setUp() {
//        questaoCreateDTO = mockQuestaoCreateDTO();
//        questaoDTO = mockQuestaoDTO();
//        questao = mockQuestao();
//    }
//
//
//    @Test
//    @DisplayName("Deveria criar uma nova questão com sucesso.")
//    public void createTest(){
//
//        //WHEN
//        when(toEntity(questaoCreateDTO))
//                .thenReturn(questao)
//                .then(invocation -> {
//                    questao.setStatus(Status.ATIVO);
//                    return questao;
//                });
//
//        when(questaoRepository.save(any()))
//                .thenReturn(questao);
//
//        when(toDTO(questao))
//                .thenReturn(questaoDTO);
//
//        //THEN
//        QuestaoDTO questaoDTOCriada = questaoService.create(questaoCreateDTO);
//
//        assertNotNull(questaoDTOCriada);
//        assertNotNull(questao);
//        assertEquals(questao.getStatus(), Status.ATIVO);
//        assertEquals(questaoDTOCriada, questaoDTO);
//
//        verify(questaoRepository, times(1)).save(any());
//
//    }
//
//
//    @Test
//    @DisplayName("Deveria atualizar uma questão setando a antiga como inativa")
//    public void updateTest() throws NaoEncontradoException {
//
//        //GIVEN
//        Integer idQuestao = anyInt();
//        Questao novaQuestao = mockQuestaoAtualizada();
//
//        //WHEN
//        when(questaoRepository.findById(idQuestao))
//                .thenReturn(Optional.of(questao))
//                .then(invocation -> {
//                    questao.setStatus(Status.INATIVO);
//                    return Optional.of(questao);
//                });
//
//        when(questaoRepository.save(questao))
//                .thenReturn(questao);
//
//        when(toEntity(questaoCreateDTO))
//                .thenReturn(novaQuestao)
//                .then(invocation -> {
//                    novaQuestao.setStatus(Status.ATIVO);
//                    return novaQuestao;
//                });
//
//        when(questaoRepository.save(novaQuestao))
//                .thenReturn(novaQuestao);
//
//        when(toDTO(novaQuestao))
//                .thenReturn(questaoDTO);
//
//        //THEN
//        QuestaoDTO questaoDTOatualizada = questaoService.update(idQuestao, questaoCreateDTO);
//
//        assertNotNull(questaoCreateDTO);
//        assertNotNull(questaoDTOatualizada);
//
//        assertNotEquals(questao.getPergunta(), novaQuestao.getPergunta());
//
//        assertEquals(questao.getStatus(), Status.INATIVO);
//        assertEquals(novaQuestao.getStatus(), Status.ATIVO);
//
//        verify(questaoRepository, times(2)).save(any());
//
//    }
//
//    @Test
//    @DisplayName("Deveria retornar uma questão pelo seu ID.")
//    public void findByIdTest() throws NaoEncontradoException {
//
//        //GIVEN
//        Optional<Questao> questaoOptional = Optional.of(mockQuestao());
//
//        //WHEN
//        when(questaoRepository.findById(anyInt()))
//                .thenReturn(questaoOptional);
//
//        when(toDTO(questao))
//                .thenReturn(questaoDTO);
//
//        //THEN
//        QuestaoDTO questao = questaoService.findById(anyInt());
//
//        assertNotNull(questaoDTO);
//
//        assertEquals(questao, questaoDTO);
//    }
//
//    @Test
//    @DisplayName("Deve preparar X questões para a prova.")
//    public void prepareQuestoesTest() throws NaoEncontradoException {
//
//        //GIVEN
//        Integer trilha = 0;
//        Integer dificuldade = 0;
//        List<Questao> mockQuestoes = List.of(questao, questao, questao, questao, questao);
//
//        //WHEN
//        when(questaoRepository.prepareQuestoes(trilha, dificuldade))
//                .thenReturn(mockQuestoes)
//                .then(invocationOnMock -> {
//                    mockQuestoes.forEach(Questao::shuffleOpcoes);
//                    return mockQuestoes;
//                });
//
//        //THEN
//        List<Questao> questoes = questaoService.prepareQuestoes(trilha, dificuldade);
//
//        assertNotNull(mockQuestoes);
//
//        assertEquals(questoes.size(), mockQuestoes.size());
//
//        assertEquals(questoes.get(0).getDificuldade(), Dificuldade.valueOf(dificuldade));
//        assertEquals(questoes.get(0).getTrilha(), Trilha.valueOf(trilha));
//    }
//
//    @Test
//    @DisplayName("Deve tonar uma questão inativa ao chamar o método deletar")
//    public void deleteTest() throws NaoEncontradoException {
//
//
//        //GIVEN
//        Integer id = anyInt();
//
//        //WHEN
//        when(questaoRepository.findById(id))
//                .thenReturn(Optional.of(questao))
//                .then(invocation -> {
//                    questao.setStatus(Status.INATIVO);
//                    return Optional.of(questao);
//                });
//
//        when(questaoRepository.save(questao))
//                .thenReturn(questao);
//
//        when(toDTO(questao))
//                .thenReturn(questaoDTO);
//
//        //THEN
//        QuestaoDTO questaoDeletada = questaoService.delete(id);
//        assertEquals(questaoDeletada, questaoDTO);
//        assertEquals(Status.INATIVO, questao.getStatus());
//
//    }
//
//    private QuestaoCreateDTO mockQuestaoCreateDTO() {
//        return new QuestaoCreateDTO(
//                "Uma pergunta para testes aqui",
//                new ArrayList<>(Arrays.asList(
//                        new Opcao("Uma primeira opção"),
//                        new Opcao("Uma segunda opção"),
//                        new Opcao("Uma terceira opção"),
//                        new Opcao("Uma Quarta opção"),
//                        new Opcao("Uma quinta opção"))),
//                "Uma primeira opção",
//                Dificuldade.FACIL,
//                Trilha.PORTUGUES);
//    }
//    private QuestaoDTO mockQuestaoDTO(){
//        return new QuestaoDTO(
//                1,
//                "Uma pergunta para testes aqui",
//                new ArrayList<>(Arrays.asList(
//                        new Opcao("Uma primeira opção"),
//                        new Opcao("Uma segunda opção"),
//                        new Opcao("Uma terceira opção"),
//                        new Opcao("Uma Quarta opção"),
//                        new Opcao("Uma quinta opção"))),
//                "Uma primeira opção",
//                5,
//                Trilha.PORTUGUES,
//                Dificuldade.FACIL,
//                Status.ATIVO);
//    }
//
//    private Questao mockQuestao(){
//        return new Questao(
//                1,
//                "Uma pergunta para testes aqui",
//                new ArrayList<>(Arrays.asList(
//                        new Opcao("Uma primeira opção"),
//                        new Opcao("Uma segunda opção"),
//                        new Opcao("Uma terceira opção"),
//                        new Opcao("Uma Quarta opção"),
//                        new Opcao("Uma quinta opção"))),
//                "Uma primeira opção",
//                5,
//                Trilha.PORTUGUES,
//                Dificuldade.FACIL,
//                Status.ATIVO,
//                new ArrayList<>(List.of(new Prova())));
//    }
//
//    private Questao mockQuestaoAtualizada(){
//        return new Questao(
//                2,
//                "Uma nova pergunta para testes aqui",
//                new ArrayList<>(Arrays.asList(
//                        new Opcao("Uma nova primeira opção"),
//                        new Opcao("Uma nova segunda opção"),
//                        new Opcao("Uma nova terceira opção"),
//                        new Opcao("Uma nova quarta opção"),
//                        new Opcao("Uma nova quinta opção"))),
//                "Uma nova primeira opção",
//                5,
//                Trilha.PORTUGUES,
//                Dificuldade.FACIL,
//                Status.ATIVO,
//                new ArrayList<>(List.of(new Prova())));
//    }
//
//    private Questao toEntity(Object o){
//        return objectMapper.convertValue(o, Questao.class);
//    }
//
//    private QuestaoDTO toDTO(Object o){
//        return objectMapper.convertValue(o, QuestaoDTO.class);
//    }
//
//}
