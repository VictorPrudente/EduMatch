package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.request.UsuarioCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Elo;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import VS13.Squad09.EduMatch.exceptions.RegraDeNegocioException;
import VS13.Squad09.EduMatch.repositories.CargoRepository;
import VS13.Squad09.EduMatch.repositories.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UsuarioService - Test")
class UsuarioServiceTest {

    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private RankingService rankingService;
    @Mock
    private CargoRepository cargoRepository;

    @InjectMocks
    private UsuarioService usuarioService;


    @Test
    @DisplayName("Deveria criar uma novo usuario com sucesso") // --> OK
    public void deveriaCriarUsuarioComSucesso() throws RegraDeNegocioException {

        UsuarioCreateDTO usuarioCreateDTOMock = retornarUsuarioCreateDTO();
        Usuario usuarioMock = retornarUsuario();
        UsuarioDTO usuarioDTOMock = retornarUsuarioDTO();

        when(objectMapper.convertValue(usuarioCreateDTOMock, Usuario.class)).thenReturn(usuarioMock);
        when(usuarioRepository.save(any())).thenReturn(usuarioMock);
        when(objectMapper.convertValue(usuarioMock, UsuarioDTO.class)).thenReturn(usuarioDTOMock);

        UsuarioDTO usuarioDTOCriada =  usuarioService.criar(usuarioCreateDTOMock);

        assertNotNull(usuarioDTOCriada);
        assertEquals(usuarioDTOCriada, usuarioDTOMock);
    }

    @Test
    public void deveriaListarComSucesso() throws BancoDeDadosException { // --> OK
        List<Usuario> listaMock = List.of(retornarUsuario(), retornarUsuario(), retornarUsuario());

        when(usuarioRepository.findAll()).thenReturn(listaMock);

        List<UsuarioDTO> listaDTORetornada = usuarioService.listarTodos();

        assertNotNull(listaDTORetornada);
        assertEquals(listaMock.size(), listaDTORetornada.size());
    }

    @Test
    public void deveriaRetornarUsuarioDTOPorId() throws RegraDeNegocioException { // --> OK
        Optional<Usuario> usuarioMock = Optional.of(retornarUsuario());
        UsuarioDTO usuarioDTOMock = retornarUsuarioDTO();
        Integer idAleatorio = new Random().nextInt();

        when(usuarioRepository.findById(anyInt())).thenReturn(usuarioMock);
        when(objectMapper.convertValue(usuarioMock.get(), UsuarioDTO.class)).thenReturn(usuarioDTOMock);

        UsuarioDTO usuarioDTORetornada =  usuarioService.getById(idAleatorio);

        assertNotNull(usuarioDTORetornada);
        assertEquals(usuarioDTORetornada, usuarioDTOMock);
    }

    @Test
    public void deveriaRetornarListaPorStatus() throws BancoDeDadosException { // --> OK
        List<Usuario> listaMock = List.of(retornarUsuario(), retornarUsuario(), retornarUsuario());

        when(usuarioRepository.findAll()).thenReturn(listaMock);

        List<UsuarioDTO> listaDTORetornada = usuarioService.listarTodos();

        assertNotNull(listaDTORetornada);
        assertEquals(listaMock.size(), listaDTORetornada.size());
    }

//    @Test
//    public void deveriaRetornarListaDeEmpresas() {
//        Set<Usuario> listaMock = Set.of(retornarUsuario(), retornarUsuario(), retornarUsuario());
//
//        when(usuarioRepository.listarEmpresas()).thenReturn(listaMock);
//
//        Set<UsuarioDTO> listaDTORetornada = usuarioService.listarEmpresas();
//
//        assertNotNull(listaDTORetornada);
//        assertEquals(listaMock.size(), listaDTORetornada.size());
//
//    }

    @Test
    public void deveriaRetornarExceptionAoReceberIdNaoExistente() { // --> OK
        Integer idNaoExistente = new Random().nextInt();

        assertThrows(RegraDeNegocioException.class, () -> usuarioService.findById(idNaoExistente));
    }

//    @Test
//    public void deveriaRemoverComSucesso() throws Exception {
//        Integer idAleatorio = new Random().nextInt();
//
//        Optional<Usuario> usuarioMock = Optional.of(retornarUsuario());
//        UsuarioDTO usuarioDTOMock = retornarUsuarioDTO();
//
//        when(usuarioRepository.findById(idAleatorio)).thenReturn(usuarioMock);
//        when(objectMapper.convertValue(usuarioMock, UsuarioDTO.class)).thenReturn(usuarioDTOMock);
//
//        usuarioService.delete(idAleatorio);
//
//        verify(usuarioRepository, times(1)).delete(usuarioMock.get());
//    }

//    @Test
//    public void deveriaAtualizarUsuarioPorId() throws RegraDeNegocioException, NaoEncontradoException {
//        UsuarioCreateDTO usuarioCreateDTOMock = retornarUsuarioCreateDTO();
//        Usuario usuarioAlterada = retornarUsuario();
//        UsuarioDTO usuarioDTOMock = retornarUsuarioDTO();
//
//        Usuario usuarioMock = new Usuario();
//        usuarioMock.setIdUsuario(1);
//        usuarioMock.setNome("Carlos");
//        usuarioMock.setSobrenome("Silva");
//        usuarioMock.setEmail("carlos@dbccompany.com.br");
//        usuarioMock.setSenha("12345");
//        usuarioMock.setCPF("12312312312");
//        usuarioMock.setCNPJ("05637396000104");
//        usuarioMock.setDataNascimento(LocalDate.parse("2000-01-01"));
//        usuarioMock.setFotoUrl("teste");
//
//        Usuario usuarioAntigo = new Usuario();
//        BeanUtils.copyProperties(usuarioMock, usuarioAntigo);
//
//
//        // ACT / WHEN
//        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(usuarioMock));
//        when(usuarioRepository.save(anyObject())).thenReturn(usuarioAlterada);
//        when(objectMapper.convertValue(usuarioAlterada, UsuarioDTO.class)).thenReturn(usuarioDTOMock);
//
//        UsuarioDTO usuarioDTORetornada = usuarioService.atualizar(usuarioMock.getIdUsuario(), usuarioCreateDTOMock);
//
//        // ASSERT / THEN
//        assertNotNull(usuarioDTORetornada);
//        assertNotEquals(usuarioAntigo, usuarioMock);
//        assertNotEquals(usuarioAntigo.getNome(), usuarioDTORetornada.getNome());
//    }
// ------------------------------------------------ MOCKS ------------------------------------------------------------

    private static UsuarioCreateDTO retornarUsuarioCreateDTO() {
        return new UsuarioCreateDTO("Rodrigo", "Silva",
                "rodrigo@dbccompany.com.br", "12345", "34908161020",
                "19641486000175", LocalDate.parse("2000-01-01"), "teste");
    }

    private static Usuario retornarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNome("Rodrigo");
        usuario.setSobrenome("Silva");
        usuario.setEmail("rodrigo@dbccompany.com.br");
        usuario.setSenha("12345");
        usuario.setCPF("34908161020");
        usuario.setCNPJ("19641486000175");
        usuario.setDataNascimento(LocalDate.parse("2000-01-01"));
        usuario.setFotoUrl("teste");

        return usuario;
    }

    public static UsuarioDTO retornarUsuarioDTO() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setIdUsuario(1);
        usuarioDTO.setNome("Rodrigo");
        usuarioDTO.setSobrenome("Silva");
        usuarioDTO.setEmail("rodrigo@dbccompany.com.br");
        usuarioDTO.setCPF("34908161020");
        usuarioDTO.setCNPJ("19641486000175");
        usuarioDTO.setDataNascimento(LocalDate.parse("2000-01-01"));
        usuarioDTO.setFotoUrl("teste");

        return usuarioDTO;
    }
}