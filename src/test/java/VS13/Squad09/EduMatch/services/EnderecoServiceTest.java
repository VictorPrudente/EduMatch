//package VS13.Squad09.EduMatch.services;
//
//import VS13.Squad09.EduMatch.dtos.mapper.EnderecoMapper;
//import VS13.Squad09.EduMatch.dtos.request.EnderecoCreateDTO;
//import VS13.Squad09.EduMatch.dtos.response.EnderecoDTO;
//import VS13.Squad09.EduMatch.entities.Endereco;
//import VS13.Squad09.EduMatch.entities.Usuario;
//import VS13.Squad09.EduMatch.entities.enums.TipoDeEndereco;
//import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
//import VS13.Squad09.EduMatch.exceptions.RegraDeNegocioException;
//import VS13.Squad09.EduMatch.repositories.EnderecoRepository;
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
//@DisplayName("EnderecoService - Test")
//class EnderecoServiceTest {
//    @Mock
//    private EnderecoMapper enderecoMapper;
//    @Mock
//    private EnderecoRepository enderecoRepository;
//    @Mock
//    private UsuarioRepository usuarioRepository;
//    @Mock
//    private UsuarioService usuarioService;
//    @InjectMocks
//    private EnderecoService enderecoService;
//
//    @Test
//    @DisplayName("Deveria criar um novo endereço para uma pessoa com sucesso")
//    public void deveriaCriarEnderecoParaPessoaComSucesso() throws RegraDeNegocioException, NaoEncontradoException {
//        EnderecoCreateDTO enderecoCreateDTOMock = retornarEnderecoCreateDTO();
//        Endereco enderecoEntityMock = retornarEnderecoEntity();
//        EnderecoDTO enderecoDTOMock = retornarEnderecoDTO();
//        Usuario usuarioEntityMock = retornarUsuarioEntitySemEndereco();
//
//        Integer idUsuario = new Random().nextInt();
//
//        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(usuarioEntityMock));
//
//        when(enderecoMapper.toEntity(enderecoCreateDTOMock)).thenReturn(enderecoEntityMock);
//        when(enderecoRepository.save(any())).thenReturn(enderecoEntityMock);
//        when(enderecoMapper.toDto(enderecoEntityMock)).thenReturn(enderecoDTOMock);
//
//        EnderecoDTO enderecoDTOcriado = enderecoService.salvar(idUsuario, enderecoCreateDTOMock);
//
//        assertEquals(enderecoDTOMock, enderecoDTOcriado);
//    }
//
//    @Test
//    @DisplayName("Deveria retornar uma exceção ao tentar criar um novo endereço para um usuário com ID inexistente")
//    public void deveriaRetornarExcecaoAoTentarCriarEnderecoParaUsuarioComIdInvalido() {
//        Integer idUsuario = new Random().nextInt();
//        EnderecoCreateDTO enderecoCreateDTOMock = retornarEnderecoCreateDTO();
//
//        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.empty());
//
//        assertThrows(NaoEncontradoException.class, () -> enderecoService.salvar(idUsuario, enderecoCreateDTOMock));
//    }
//
//    @Test
//    @DisplayName("Deveria retornar uma exceção ao tentar criar um novo endereço para uma pessoa que já possui um")
//    public void deveriaRetornarExcecaoAoTentarCriarEnderecoParaPessoaQueJaPossuiUm() {
//        Usuario usuarioEntityMock = retornarUsuarioEntityComEndereco();
//        EnderecoCreateDTO enderecoCreateDTOMock = retornarEnderecoCreateDTO();
//
//        Integer idUsuario = new Random().nextInt();
//
//        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(usuarioEntityMock));
//
//        assertThrows(RegraDeNegocioException.class, () -> enderecoService.salvar(idUsuario, enderecoCreateDTOMock));
//    }
//
//    @Test
//    @DisplayName("Deveria atualizar o endereço de uma pessoa com sucesso")
//    public void deveriaAtualizarEnderecoDePessoaComSucesso() throws NaoEncontradoException {
//        Endereco enderecoEntityMock = new Endereco();
//
//        enderecoEntityMock.setId(1);
//        enderecoEntityMock.setLogradouro("Rua das Anunciações");
//        enderecoEntityMock.setNumero(501);
//        enderecoEntityMock.setTipoDeEndereco(TipoDeEndereco.RESIDENCIAL);
//        enderecoEntityMock.setComplemento("Próximo ao shopping");
//        enderecoEntityMock.setCep("87654321");
//        enderecoEntityMock.setCidade("Ceará");
//        enderecoEntityMock.setEstado("CE");
//        enderecoEntityMock.setPais("Brasil");
//        enderecoEntityMock.setIdUsuario(1);
//
//        Endereco enderecoEntityAntigo = new Endereco();
//        BeanUtils.copyProperties(enderecoEntityMock, enderecoEntityAntigo);
//
//        EnderecoCreateDTO enderecoCreateDTOMock = retornarEnderecoCreateDTO();
//        Endereco enderecoAlterado = retornarEnderecoEntity();
//        EnderecoDTO enderecoDTOMock = retornarEnderecoDTO();
//
//        when(enderecoRepository.findById(anyInt())).thenReturn(Optional.of(enderecoEntityMock));
//        when(enderecoRepository.save(any())).thenReturn(enderecoAlterado);
//        when(enderecoMapper.toDto(enderecoAlterado)).thenReturn(enderecoDTOMock);
//
//        EnderecoDTO enderecoDTORetornado = enderecoService.atualizar(enderecoEntityMock.getId(), enderecoCreateDTOMock);
//
//        assertNotNull(enderecoDTORetornado);
//        assertNotEquals(enderecoEntityAntigo, enderecoEntityMock);
//        assertNotEquals(enderecoEntityAntigo.getLogradouro(), enderecoDTORetornado.getLogradouro());
//    }
//
//    @Test
//    @DisplayName("Deveria retornar uma exceção ao tentar atualizar um endereço inexistente")
//    public void deveriaRetornarExcecaoAoTentarAtualizarContatoInexistente() {
//        Integer idAleatorio = new Random().nextInt();
//
//        EnderecoCreateDTO contatoCreateDTOMock = retornarEnderecoCreateDTO();
//
//        when(enderecoRepository.findById(anyInt())).thenReturn(Optional.empty());
//
//        assertThrows(NaoEncontradoException.class, () -> enderecoService.atualizar(idAleatorio, contatoCreateDTOMock));
//    }
//
//    @Test
//    @DisplayName("Deveria deletar o endereço de uma pessoa com sucesso")
//    public void deveriaDeletarEnderecoDePessoaComSucesso() throws NaoEncontradoException {
//        Integer idEndereco = new Random().nextInt();
//
//        Endereco enderecoEntityMock = retornarEnderecoEntity();
//
//        when(enderecoRepository.findById(anyInt())).thenReturn(Optional.of(enderecoEntityMock));
//
//        enderecoService.deletar(idEndereco);
//
//        verify(enderecoRepository, times(1)).delete(enderecoEntityMock);
//    }
//
//    @Test
//    @DisplayName("Deveria retornar o endereço por um id com sucesso")
//    public void deveriaRetornarEnderecoPorIdComSucesso() throws NaoEncontradoException{
//        Endereco enderecoEntityMock = retornarEnderecoEntity();
//
//        Integer idEndereco = new Random().nextInt();
//
//        when(enderecoRepository.findById(anyInt())).thenReturn(Optional.of(enderecoEntityMock));
//
//        EnderecoDTO enderecoDTOMock = enderecoMapper.toDto(enderecoEntityMock);
//
//        EnderecoDTO enderecoDTORetornado = enderecoService.findById(idEndereco);
//
//        assertEquals(enderecoDTOMock, enderecoDTORetornado);
//    }
//
//    @Test
//    @DisplayName("Deveria retornar um endereço por id do usuario com sucesso")
//    public void deveriaRetornarEnderecoPorIdDoUsuario() throws NaoEncontradoException{
//        Endereco enderecoEntityMock = retornarEnderecoEntity();
//
//        Integer idUsuario = new Random().nextInt();
//
//        when(enderecoRepository.findEnderecoByIdUsuario(anyInt())).thenReturn(Optional.of(enderecoEntityMock));
//
//        EnderecoDTO enderecoDTORetornado = enderecoService.findEnderecoByIdUsuario(idUsuario);
//
//        EnderecoDTO enderecoDTOMock = enderecoMapper.toDto(enderecoEntityMock);
//
//        assertEquals(enderecoDTORetornado, enderecoDTOMock);
//    }
//
//    private static EnderecoCreateDTO retornarEnderecoCreateDTO() {
//        EnderecoCreateDTO enderecoCreateDTO = new EnderecoCreateDTO("Rua das ligações", 105, TipoDeEndereco.RESIDENCIAL, "Casa", "12345678", "São Paulo", "SP", "Brasil");
//
//        return enderecoCreateDTO;
//    }
//
//    private static Endereco retornarEnderecoEntity() {
//        Endereco enderecoEntity = new Endereco();
//
//        enderecoEntity.setId(1);
//        enderecoEntity.setLogradouro("Rua das ligações");
//        enderecoEntity.setNumero(105);
//        enderecoEntity.setTipoDeEndereco(TipoDeEndereco.RESIDENCIAL);
//        enderecoEntity.setComplemento("Casa");
//        enderecoEntity.setCep("12345678");
//        enderecoEntity.setCidade("São Paulo");
//        enderecoEntity.setEstado("SP");
//        enderecoEntity.setPais("Brasil");
//        enderecoEntity.setIdUsuario(1);
//
//        return enderecoEntity;
//    }
//
//    private static EnderecoDTO retornarEnderecoDTO() {
//        EnderecoDTO enderecoDTO = new EnderecoDTO();
//
//        enderecoDTO.setId(1);
//        enderecoDTO.setLogradouro("Rua das ligações");
//        enderecoDTO.setNumero(105);
//        enderecoDTO.setTipoDeEndereco(TipoDeEndereco.RESIDENCIAL);
//        enderecoDTO.setComplemento("Casa");
//        enderecoDTO.setCep("12345678");
//        enderecoDTO.setCidade("São Paulo");
//        enderecoDTO.setEstado("SP");
//        enderecoDTO.setPais("Brasil");
//
//        return enderecoDTO;
//    }
//
//    private static Usuario retornarUsuarioEntitySemEndereco() {
//        Usuario usuarioEntity = new Usuario();
//
//        usuarioEntity.setIdUsuario(1);
//
//        return usuarioEntity;
//    }
//
//    private static Usuario retornarUsuarioEntityComEndereco() {
//        Endereco enderecoEntityMock = retornarEnderecoEntity();
//
//        Usuario usuarioEntity = new Usuario();
//
//        usuarioEntity.setIdUsuario(1);
//        usuarioEntity.setEndereco(enderecoEntityMock);
//
//        return usuarioEntity;
//    }
//}
