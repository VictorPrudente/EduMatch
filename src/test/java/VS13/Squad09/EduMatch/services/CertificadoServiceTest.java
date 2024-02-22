package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.request.CertificadoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.CertificadoDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.entities.Certificado;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.entities.enums.Dificuldade;
import VS13.Squad09.EduMatch.entities.enums.Trilha;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import VS13.Squad09.EduMatch.repositories.CertificadoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("CertificadoService - Test")
class CertificadoServiceTest {
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private CertificadoRepository certificadoRepository;

    @Mock
    private UsuarioService usuarioService;

    @Spy
    @InjectMocks
    private CertificadoService certificadoService;


    @Test
    @DisplayName("Deveria criar um novo certificado com sucesso")
    public void deveriaCriarCertificadoComSucesso() throws Exception {
       Integer idUsuario = 1;
       CertificadoCreateDTO certificadoCreateMock = retornarCertificadoCreateDTO();
       Certificado certificadoMock = retornarCertificado();
       CertificadoDTO certificadoDTOMock = retornarCertificadoDTO();
       Usuario usuario = retornarUsuario();
       UsuarioDTO usuarioDTO = retornarUsuarioDTO();

       doReturn(usuarioDTO).when(usuarioService).listarPorId(idUsuario);
       when(objectMapper.convertValue(usuarioDTO, Usuario.class)).thenReturn(usuario);
       when(objectMapper.convertValue(certificadoCreateMock, Certificado.class)).thenReturn(certificadoMock);
       when(certificadoRepository.save(any())).thenReturn(certificadoMock);
       when(objectMapper.convertValue(certificadoMock,CertificadoDTO.class)).thenReturn(certificadoDTOMock);

       CertificadoDTO certificadoDTOCriado =  certificadoService.criar(idUsuario, certificadoCreateMock);

        assertNotNull(certificadoDTOCriado);
        assertEquals(certificadoDTOCriado, certificadoDTOMock);
    }

    @Test
    public void deveriaListarComSucesso() throws Exception {
        List<Certificado> listaMock = List.of(retornarCertificado(), retornarCertificado(), retornarCertificado());
        List<CertificadoDTO> listaMockDTO = List.of(retornarCertificadoDTO(), retornarCertificadoDTO(), retornarCertificadoDTO());

        when(certificadoRepository.findAll()).thenReturn(listaMock);

        List<CertificadoDTO> listaDTORetornada = certificadoService.listarTodos();

        assertNotNull(listaDTORetornada);
        assertEquals(listaMockDTO.size(), listaDTORetornada.size());
    }

    // ------------------------------------------------ MOCKS ----------------------------------------------------------
    private static CertificadoCreateDTO retornarCertificadoCreateDTO() throws NaoEncontradoException {
        return new CertificadoCreateDTO(Trilha.valueOf(1),Dificuldade.valueOf(2));
    }

    private static Certificado retornarCertificado() throws NaoEncontradoException {
        Usuario usuario = retornarUsuario();

        Certificado certificado = new Certificado();
        certificado.setTrilha(Trilha.valueOf(1));
        certificado.setDificuldade(Dificuldade.valueOf(2));
        certificado.setId_certificado(1);
        certificado.setConclusao(LocalDateTime.now());
        certificado.setUsuario(usuario);

        return certificado;
    }
    public static CertificadoDTO retornarCertificadoDTO() throws NaoEncontradoException {
        Usuario usuario = retornarUsuario();


        CertificadoDTO certificadoDTO = new CertificadoDTO();
        certificadoDTO.setConclusao(LocalDateTime.now());
        certificadoDTO.setTrilha(Trilha.valueOf(1));
        certificadoDTO.setDificuldade(Dificuldade.valueOf(2));
        certificadoDTO.setUsuario(usuario);

        return certificadoDTO;
    }

    private static Usuario retornarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNome("Rodrigo");
        usuario.setSobrenome("Silva");
        usuario.setEmail("rodrigo@dbccompany.com.br");
        usuario.setSenha("12345");
        usuario.setCPF("34908161020");
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
        usuarioDTO.setDataNascimento(LocalDate.parse("2000-01-01"));
        usuarioDTO.setFotoUrl("teste");

        return usuarioDTO;
    }
}