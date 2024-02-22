package VS13.Squad09.EduMatch.services;


import VS13.Squad09.EduMatch.dtos.request.prova.ProvaFinishCreateDTO;
import VS13.Squad09.EduMatch.dtos.request.prova.ProvaStartCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.dtos.response.prova.ProvaFinishDTO;
import VS13.Squad09.EduMatch.dtos.response.prova.ProvaStartDTO;
import VS13.Squad09.EduMatch.entities.*;
import VS13.Squad09.EduMatch.entities.enums.*;
import VS13.Squad09.EduMatch.exceptions.RegraDeNegocioException;
import VS13.Squad09.EduMatch.repositories.ProvaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProvaService - Testes")
public class ProvaServiceTest {

    @Mock
    ProvaRepository provaRepository;
    @Mock
    QuestaoService questaoService;
    @Mock
    UsuarioService usuarioService;
    @Mock
    InsigniaService insigniaService;
    @Mock
    ObjectMapper objectMapper;
    @Spy
    @InjectMocks
    ProvaService provaService;

    ProvaStartCreateDTO provaStartCreateDTO;
    ProvaStartDTO provaStartDTO;
    Prova prova;
    Usuario usuario;
    UsuarioDTO usuarioDTO;
    ProvaFinishCreateDTO provaFinishCreateDTO;

    @BeforeEach
    public void setUp(){
        this.provaStartCreateDTO = mockProvaCreateStartDTO();
        this.usuarioDTO = mockUsuarioDTO();
        this.usuario = mockUsuario();
        this.prova = mockProva();
        this.provaStartDTO = mockProvaStartDTO();
        this.provaFinishCreateDTO = mockRespostas();
    }


    @Test
    @DisplayName("Deve iniciar a prova e setar os atributos")
    public void startTest() throws Exception {

        //GIVEN
        Integer tempo = 300;
        Integer aprovacao = 60;

        //WHEN
        when(usuarioService.listarPorId(anyInt()))
                .thenReturn(usuarioDTO);

        when(objectMapper.convertValue(usuarioDTO, Usuario.class))
                .thenReturn(usuario);

        when(toEntity(provaStartCreateDTO))
                .thenReturn(prova)
                .then(invocationOnMock -> {
                    prova.setDataFinal(LocalDateTime.now());
                    prova.setTempoLimite(tempo);
                    prova.setUsuario(usuario);
                    prova.setStatus(Status.ATIVO);
                    prova.setQuestoes(mockQuestoes());
                    prova.setTotalQuestoes(mockQuestoes().size());
                    return prova;
                });

        when(provaRepository.save(prova))
                .thenReturn(prova);

        when(toDTO(prova))
                .thenReturn(provaStartDTO);

        //THEN
        ProvaStartDTO provaStart = provaService.startTest(provaStartCreateDTO);

        assertNotNull(provaStart);
        assertEquals(prova.getStatus(), Status.ATIVO);
    }

    @Test
    @DisplayName("Deveria finalizar a prova, verificar a pontuação e atualizar o usuário.")
    public void finishProvaTest() throws Exception {

        //GIVEN
        Integer idProva = anyInt();


        //WHEN
        when(provaRepository.findById(idProva))
                .thenReturn(Optional.of(prova))
                .then(invocationOnMock -> {
                    prova.setRespostas(mockRespostas().getRespostas());
                    return prova;
                });


        //THEN
        ProvaFinishDTO provaFinishDTO = provaService.finishTest(idProva, provaFinishCreateDTO);

        assertNotNull(provaFinishDTO);

    }

    @Test
    @DisplayName("Deveria lançar excessão na prova caso o tempo seja inválido.")
    public void validarProvaTest() throws Exception {

        //GIVEN


        //WHEN
        try{
            validarProva(prova);
            fail("Regra de negócio");
            prova.setStatus(Status.INATIVO);
        } catch (RegraDeNegocioException e){
            assertEquals("Tempo esgotado.", e.getMessage());
        }


        //THEN


    }



    private ProvaStartCreateDTO mockProvaCreateStartDTO(){
        return new ProvaStartCreateDTO(
                1,
                Trilha.PORTUGUES,
                Dificuldade.FACIL
        );
    }


    private ProvaStartDTO mockProvaStartDTO(){
        return new ProvaStartDTO(
                1,
                new HashSet<>(Arrays.asList(
                        new Questao(),
                        new Questao(),
                        new Questao(),
                        new Questao(),
                        new Questao())) {
                },
                usuario,
                LocalDateTime.now(),
                300,
                5
        );
    }

    private Prova mockProva(){
       return new Prova(
               1,
               new ArrayList<>(Arrays.asList(
                        new Questao(),
                        new Questao(),
                        new Questao(),
                        new Questao(),
                        new Questao())),
        usuario,
        new ArrayList<>(Arrays.asList(
                new Resposta(),
                new Resposta(),
                new Resposta(),
                new Resposta(),
                new Resposta())),
                LocalDateTime.now(),
        LocalDateTime.of(2024, 2, 19, 10, 10, 00),
        300,
        200,
        5,
        5,
        100.00,
                Resultado.APROVADO,
                Status.ATIVO);
    }

    private ProvaFinishCreateDTO mockRespostas(){
        return new ProvaFinishCreateDTO(
                new ArrayList<>(Arrays.asList(
                new Resposta("A"),
                new Resposta("B"),
                new Resposta("C"),
                new Resposta("D"),
                new Resposta("E"))));
    }


    private List<Questao> mockQuestoes(){
        return questaoService.prepareQuestoes(0, 0);
    }
    private Usuario mockUsuario(){
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(1);
            usuario.setNome("Rodrigo");
            usuario.setSobrenome("Silva");
            usuario.setEmail("rodrigo@dbccompany.com.br");
            usuario.setSenha("12345");
            usuario.setCNPJ("19641486000175");
            usuario.setDataNascimento(LocalDate.parse("2000-01-01"));
            usuario.setFotoUrl("teste");
            usuario.setStatus(Status.ATIVO);
            usuario.setPontuacao(0);
            usuario.setMoedas(0);
            usuario.setElo(Elo.FERRO);

            return usuario;
    }

    private UsuarioDTO mockUsuarioDTO(){
        return new UsuarioDTO(
        1,
        "Rodrigo",
        "Silva",
        0,
        "rodrigo@dbccompany.com.br");
    }

    private Prova toEntity(Object o){
        return objectMapper.convertValue(o, Prova.class);
    }

    private ProvaStartDTO toDTO(Object o){
        return objectMapper.convertValue(o, ProvaStartDTO.class);
    }

    private void validarProva(Prova prova) throws RegraDeNegocioException {
        if(prova.getStatus().equals(Status.INATIVO)){
            throw new RegraDeNegocioException("Prova já finalizada.");
        }

        Duration duracao = Duration.between(prova.getDataInicio(), prova.getDataFinal());
        long tempoUsado = Math.abs(duracao.getSeconds());
        if(tempoUsado > prova.getTempoLimite()){
            prova.setStatus(Status.INATIVO);
            provaRepository.save(prova);
            throw new RegraDeNegocioException("Tempo esgotado.");
        }
    }

}
