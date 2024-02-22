package VS13.Squad09.EduMatch.services;


import VS13.Squad09.EduMatch.dtos.request.InsigniaCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.InsigniaDTO;
import VS13.Squad09.EduMatch.entities.Insignia;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Status;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import VS13.Squad09.EduMatch.repositories.InsigniaRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("InsigniaService - Testes")
public class InsigniaServiceTest {


    @Mock
    InsigniaRepository insigniaRepository;

    @Mock
    ObjectMapper objectMapper;

    @Spy
    @InjectMocks
    InsigniaService insigniaService;

    InsigniaCreateDTO insigniaCreateDTO;
    InsigniaDTO insigniaDTO;
    Insignia insignia;

    @BeforeEach
    public void setUp(){
        this.insigniaCreateDTO = mockInsigniaCreateDTO();
        this.insigniaDTO = mockInsigniaDTO();
        this.insignia = mockInsignia();
    }


    @Test
    @DisplayName("Deveria criar uma insignia e setar o status dela como ativa")
    public void createTest() {

        //WHEN
        when(toEntity(insigniaCreateDTO))
                .thenReturn(insignia)
                .then(invocation -> {
                    insignia.setStatus(Status.ATIVO);
                    return insignia;
                });

        when(insigniaRepository.save(any()))
                .thenReturn(insignia);

        when(toDTO(insignia))
                .thenReturn(insigniaDTO);

        //THEN
        InsigniaDTO insigniaDTOCriada = insigniaService.create(insigniaCreateDTO);

        assertNotNull(insigniaDTOCriada);
        assertNotNull(insignia);
        assertEquals(insignia.getStatus(), Status.ATIVO);
        assertEquals(insigniaDTOCriada, insigniaDTO);

        verify(insigniaRepository, times(1)).save(any());

    }

    @Test
    @DisplayName("Deveria atualizar uma insignia")
    public void updateTest() throws NaoEncontradoException {

        //GIVEN
        Integer idInsignia = anyInt();
        Insignia novaInsignia = mockInsigniaAtualizada();

        //WHEN
        when(insigniaRepository.findById(idInsignia))
                .thenReturn(Optional.of(insignia));

        when(toEntity(insigniaCreateDTO))
                .thenReturn(novaInsignia)
                .then(invocationOnMock -> {
                    novaInsignia.setId(insignia.getId());
                    return novaInsignia;
                });

        when(insigniaRepository.save(novaInsignia))
                .thenReturn(novaInsignia);

        when(toDTO(novaInsignia))
                .thenReturn(insigniaDTO);

        //THEN
        InsigniaDTO insigniaDTOAtualizada = insigniaService.update(idInsignia, insigniaCreateDTO);

        assertNotNull(insigniaCreateDTO);
        assertNotNull(insigniaDTOAtualizada);
        assertNotNull(novaInsignia);

        assertEquals(novaInsignia.getId(), insignia.getId());

        verify(insigniaRepository, times(1)).save(any());

    }

    @Test
    @DisplayName("Deve listar todas insignias recebendo o id do usuário")
    public void findByUserAll()  {

        //GIVEN
        List<InsigniaDTO> mockInsignias = List.of(insigniaDTO);
        Integer idUsuario = anyInt();

        //WHEN - lenient().
        lenient().when(insigniaRepository.findAllByOwner(1))
                .thenReturn(mockInsignias);

        //THEN
        List<InsigniaDTO> insignias = insigniaService.findByUser(idUsuario, null);

        assertNotNull(insignias);

        verify(insigniaRepository, times(1)).findAllByOwner(idUsuario);

    }

    @Test
    @DisplayName("Deve listar uma insignia recebendo o id do usuário")
    public void findByUserOne()  {

        //GIVEN
        Integer idInsignia = anyInt();
        Integer idUsuario = anyInt();
        List<InsigniaDTO> mockInsignias = List.of(insigniaDTO);

        //WHEN - lenient().
        lenient().when(insigniaRepository.findOneByOwner(idUsuario, idInsignia))
                .thenReturn(mockInsignias);

        //THEN
        List<InsigniaDTO> insignias = insigniaService.findByUser(anyInt(), anyInt());

        assertNotNull(insignias);

        verify(insigniaRepository, times(1)).findOneByOwner(idUsuario, idInsignia);
    }

    @Test
    @DisplayName("Deve listar uma insignias recebendo o id")
    public void findOneInsigniaTest() throws NaoEncontradoException {

        //GIVEN
        List<InsigniaDTO> mockInsignias = new ArrayList<>();

        //WHEN
        when(insigniaRepository.findInsignia(anyInt()))
                .thenReturn(mockInsignias);

        //THEN
        List<InsigniaDTO> insignias = insigniaService.findInsignias(anyInt());

        assertNotNull(insignias);
    }

    @Test
    @DisplayName("Deve listar todas as insignias")
    public void findAllInsigniasTest() {

        //GIVEN
        List<InsigniaDTO> mockInsignias = new ArrayList<>();

        //WHEN
        when(insigniaRepository.findInsignias())
                .thenReturn(mockInsignias);

        //THEN
        List<InsigniaDTO> insignias = insigniaService.findInsignias(null);

        assertNotNull(insignias);
    }


    @Test
    @DisplayName("Deveria adicionar uma insignia a um usuário.")
    public void addUsuarioTest() throws Exception {

        //GIVEN
        Usuario usuario = new Usuario();
        String tag = anyString();

        //WHEN
        when(insigniaRepository.findByTagIgnoreCase(tag))
                .thenReturn(insignia)
                .then(invocationOnMock -> {
                    insignia.getUsuarios().add(usuario);
                    return insignia;
                });

        when(insigniaRepository.save(insignia))
                .thenReturn(insignia);

        //THEN
        insigniaService.addUsuario(usuario, tag);

        assertNotNull(usuario);
        assertNotNull(tag);
        assert(insignia.getUsuarios().contains(usuario));

    }

    private InsigniaCreateDTO mockInsigniaCreateDTO(){
        return new InsigniaCreateDTO(
                "https://raw.githubusercontent.com/VictorPrudente/EduMatch/spring/migration/src/main/resources/images/portugues_facil.png",
                "Português Fácil",
                "Esta insignia é recompensada a todos os usuários que completaram a prova de Português na dificuldade Fácil",
                "PORTUGUES_FACIL"
        );
    }

    private InsigniaDTO mockInsigniaDTO(){
       return new InsigniaDTO(
               1,
               "https://raw.githubusercontent.com/VictorPrudente/EduMatch/spring/migration/src/main/resources/images/portugues_facil.png",
               "Português Fácil",
               "Esta insignia é recompensada a todos os usuários que completaram a prova de Português na dificuldade Fácil",
               "PORTUGUES_FACIL",
               Status.ATIVO,
               new HashSet<>(Set.of(new Usuario())));
    }

    private Insignia mockInsignia(){
        return new Insignia(
                1,
                "https://raw.githubusercontent.com/VictorPrudente/EduMatch/spring/migration/src/main/resources/images/portugues_facil.png",
                "Português Fácil",
                "Esta insignia é recompensada a todos os usuários que completaram a prova de Português na dificuldade Fácil",
                "PORTUGUES_FACIL",
                Status.ATIVO,
                new HashSet<>(Set.of(new Usuario())));
    }

    private Insignia mockInsigniaAtualizada(){
        return new Insignia(
                1,
                "https://raw.githubusercontent.com/VictorPrudente/EduMatch/spring/migration/src/main/resources/images/portugues_facil.png",
                "Português Mais Fácil",
                "Esta insignia é recompensada a todos os usuários que completaram a prova de Português na dificuldade Fácil",
                "PORTUGUES_MAIS_FACIL",
                Status.ATIVO,
                new HashSet<>(Set.of(new Usuario())));
    }


    private Insignia toEntity(Object o){
        return objectMapper.convertValue(o, Insignia.class);
    }

    private InsigniaDTO toDTO(Object o){
        return objectMapper.convertValue(o, InsigniaDTO.class);
    }
}
