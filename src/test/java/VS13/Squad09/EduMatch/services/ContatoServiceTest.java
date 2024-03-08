//package VS13.Squad09.EduMatch.services;
//
//import VS13.Squad09.EduMatch.dtos.mapper.ContatoMapper;
//import VS13.Squad09.EduMatch.dtos.request.ContatoCreateDTO;
//import VS13.Squad09.EduMatch.dtos.response.ContatoDTO;
//import VS13.Squad09.EduMatch.entities.Contato;
//import VS13.Squad09.EduMatch.entities.Usuario;
//import VS13.Squad09.EduMatch.entities.enums.TipoDeContato;
//import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
//import VS13.Squad09.EduMatch.exceptions.RegraDeNegocioException;
//import VS13.Squad09.EduMatch.repositories.ContatoRepository;
//import VS13.Squad09.EduMatch.repositories.UsuarioRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.BeanUtils;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@DisplayName("ContatoService - Test")
//class ContatoServiceTest {
//    @Mock
//    private ContatoMapper contatoMapper;
//    @Mock
//    private ContatoRepository contatoRepository;
//    @Mock
//    private UsuarioRepository usuarioRepository;
//    @Mock
//    private UsuarioService usuarioService;
//    @InjectMocks
//    private ContatoService contatoService;
//
//    @Test
//    @DisplayName("Deveria criar um novo contato para uma pessoa com sucesso")
//    public void deveriaCriarContatoParaPessoaComSucesso() throws RegraDeNegocioException, NaoEncontradoException {
//        ContatoCreateDTO contatoCreateDTOMock = retornarContatoCreateDTO();
//        Contato contatoEntityMock = retornarContatoEntity();
//        ContatoDTO contatoDTOMock = retornarContatoDTO();
//        Usuario usuarioEntityMock = retornarUsuarioEntitySemContato();
//
//        Integer idUsuario = new Random().nextInt();
//
//        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(usuarioEntityMock));
//
//        when(contatoMapper.toEntity(contatoCreateDTOMock)).thenReturn(contatoEntityMock);
//        when(contatoRepository.save(any())).thenReturn(contatoEntityMock);
//        when(contatoMapper.toDto(contatoEntityMock)).thenReturn(contatoDTOMock);
//
//        ContatoDTO contaDTOcriado = contatoService.salvar(idUsuario, contatoCreateDTOMock);
//
//        assertNotNull(contaDTOcriado);
//        assertEquals(contatoDTOMock, contaDTOcriado);
//    }
//
//    @Test
//    @DisplayName("Deveria retornar uma exceção ao tentar criar um novo contato para um usuário com ID inexistente")
//    public void deveriaRetornarExcecaoAoTentarCriarContatoParaUsuarioComIdInvalido() {
//        Integer idUsuario = new Random().nextInt();
//        ContatoCreateDTO contatoCreateDTOMock = retornarContatoCreateDTO();
//
//        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.empty());
//
//        assertThrows(NaoEncontradoException.class, () -> contatoService.salvar(idUsuario, contatoCreateDTOMock));
//    }
//
//    @Test
//    @DisplayName("Deveria retornar uma exceção ao tentar criar um novo contato para uma pessoa que já possui um")
//    public void deveriaRetornarExcecaoAoTentarCriarContatoParaPessoaQueJaPossuiUm() {
//        Usuario usuarioEntityMock = retornarUsuarioEntityComContato();
//        ContatoCreateDTO contatoCreateDTOMock = retornarContatoCreateDTO();
//
//        Integer idUsuario = new Random().nextInt();
//
//        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(usuarioEntityMock));
//
//        assertThrows(RegraDeNegocioException.class, () -> contatoService.salvar(idUsuario, contatoCreateDTOMock));
//    }
//
//    @Test
//    @DisplayName("Deveria atualizar o contato de uma pessoa com sucesso")
//    public void deveriaAtualizarContatoDePessoaComSucesso() throws NaoEncontradoException {
//        Contato contatoEntityMock = new Contato();
//
//        contatoEntityMock.setId(1);
//        contatoEntityMock.setDescricao("Contato para whatsapp");
//        contatoEntityMock.setTelefone("21996543216");
//        contatoEntityMock.setTipo(TipoDeContato.RESIDENCIAL);
//        contatoEntityMock.setIdUsuario(1);
//
//        Contato contatoEntityAntigo = new Contato();
//        BeanUtils.copyProperties(contatoEntityMock, contatoEntityAntigo);
//
//        ContatoCreateDTO contatoCreateDTOMock = retornarContatoCreateDTO();
//        Contato contatoAlterado = retornarContatoEntity();
//        ContatoDTO contatoDTOMock = retornarContatoDTO();
//
//        when(contatoRepository.findById(anyInt())).thenReturn(Optional.of(contatoEntityMock));
//        when(contatoRepository.save(any())).thenReturn(contatoAlterado);
//        when(contatoMapper.toDto(contatoAlterado)).thenReturn(contatoDTOMock);
//
//        ContatoDTO contatoDTORetornado = contatoService.atualizar(contatoEntityMock.getId(), contatoCreateDTOMock);
//
//        assertNotNull(contatoDTORetornado);
//        assertNotEquals(contatoEntityAntigo, contatoEntityMock);
//        assertNotEquals(contatoEntityAntigo.getTelefone(), contatoDTORetornado.getTelefone());
//    }
//
//    @Test
//    @DisplayName("Deveria retornar uma exceção ao tentar atualizar um contato inexistente")
//    public void deveriaRetornarExcecaoAoTentarAtualizarContatoInexistente() {
//        Integer idAleatorio = new Random().nextInt();
//
//        ContatoCreateDTO contatoCreateDTOMock = retornarContatoCreateDTO();
//
//        when(contatoRepository.findById(anyInt())).thenReturn(Optional.empty());
//
//        assertThrows(NaoEncontradoException.class, () -> contatoService.atualizar(idAleatorio, contatoCreateDTOMock));
//    }
//
//    @Test
//    @DisplayName("Deveria deletar o contato de uma pessoa com sucesso")
//    public void deveriaDeletarContatoDePessoaComSucesso() throws NaoEncontradoException {
//        Integer idContato = new Random().nextInt();
//
//        Contato contatoEntityMock = retornarContatoEntity();
//
//        when(contatoRepository.findById(anyInt())).thenReturn(Optional.of(contatoEntityMock));
//
//        contatoService.deletar(idContato);
//
//        verify(contatoRepository, times(1)).delete(contatoEntityMock);
//    }
//
//    @Test
//    @DisplayName("Deveria retornar o contato por um id com sucesso")
//    public void deveriaRetornarContatoPorIdComSucesso() throws NaoEncontradoException{
//        Contato contatoEntityMock = retornarContatoEntity();
//
//        Integer idContato = new Random().nextInt();
//
//        when(contatoRepository.findById(anyInt())).thenReturn(Optional.of(contatoEntityMock));
//
//        ContatoDTO contatoDTOMock = contatoMapper.toDto(contatoEntityMock);
//
//        ContatoDTO contatoDTORetornado = contatoService.findById(idContato);
//
//        assertEquals(contatoDTOMock, contatoDTORetornado);
//    }
//
//    @Test
//    @DisplayName("Deveria retornar um contato por id do usuario com sucesso")
//    public void deveriaRetornarContatoPorIdDoUsuario() throws NaoEncontradoException{
//        Contato contatoEntityMock = retornarContatoEntity();
//
//        Integer idUsuario = new Random().nextInt();
//
//        when(contatoRepository.findContatoByIdUsuario(anyInt())).thenReturn(Optional.of(contatoEntityMock));
//
//        ContatoDTO contatoDTORetornado = contatoService.findContatoByIdUsuario(idUsuario);
//
//        ContatoDTO contatoDTOMock = contatoMapper.toDto(contatoEntityMock);
//
//        assertEquals(contatoDTORetornado, contatoDTOMock);
//    }
//
//    private static ContatoCreateDTO retornarContatoCreateDTO() {
//        ContatoCreateDTO contatoCreateDTO = new ContatoCreateDTO("Apenas ligações", "21998561265", TipoDeContato.RESIDENCIAL);
//
//        return contatoCreateDTO;
//    }
//
//    private static Contato retornarContatoEntity() {
//        Contato contatoEntity = new Contato();
//
//        contatoEntity.setId(1);
//        contatoEntity.setDescricao("Apenas ligações");
//        contatoEntity.setTelefone("21998561265");
//        contatoEntity.setTipo(TipoDeContato.RESIDENCIAL);
//        contatoEntity.setIdUsuario(1);
//
//        return contatoEntity;
//    }
//
//    private static ContatoDTO retornarContatoDTO() {
//        ContatoDTO contatoDTO = new ContatoDTO();
//
//        contatoDTO.setId(1);
//        contatoDTO.setDescricao("Apenas ligações");
//        contatoDTO.setTelefone("21998561265");
//        contatoDTO.setTipo(TipoDeContato.RESIDENCIAL);
//
//        return contatoDTO;
//    }
//
//    private static Usuario retornarUsuarioEntitySemContato() {
//        Usuario usuarioEntity = new Usuario();
//
//        usuarioEntity.setIdUsuario(1);
//
//        return usuarioEntity;
//    }
//
//    private static Usuario retornarUsuarioEntityComContato() {
//        Contato contatoEntityMock = retornarContatoEntity();
//
//        Usuario usuarioEntity = new Usuario();
//
//        usuarioEntity.setIdUsuario(1);
//        usuarioEntity.setContato(contatoEntityMock);
//
//        return usuarioEntity;
//    }
//}
