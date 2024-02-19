package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.request.CertificadoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.CertificadoDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.entities.Certificado;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.exceptions.RegraDeNegocioException;
import VS13.Squad09.EduMatch.repositories.CertificadoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class CertificadoService {

    private final CertificadoRepository certificadoRepository;
    private final UsuarioService usuarioService;
    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    public CertificadoDTO criar(Integer idUsuario, CertificadoCreateDTO certificado) throws Exception {
        log.debug("Criando Certificado...");
        UsuarioDTO usuarioDTO = usuarioService.listarPorId(idUsuario);
        Usuario usuario = objectMapper.convertValue(usuarioDTO, Usuario.class);
        Certificado certificadoEntity = objectMapper.convertValue(certificado, Certificado.class);

        certificadoEntity.setConclusao(LocalDateTime.now());
        usuario.getCertificados().add(certificadoEntity);
        certificadoRepository.save(certificadoEntity);
        // emailService.sendEmail(certificadoEntity.getUsuario(), certificadoEntity,4);

        CertificadoDTO certificadoDTO = objectMapper.convertValue(certificadoEntity, CertificadoDTO.class);

        return certificadoDTO;
    }

    public List<CertificadoDTO> listarTodos() throws Exception {
        log.debug("Listando Certificados...");
        return certificadoRepository.findAll().stream().map(certificado ->
                        objectMapper.convertValue(certificado, CertificadoDTO.class))
                .collect(Collectors.toList());
    }

    public CertificadoDTO listarUltimo(Integer usuarioId) throws RegraDeNegocioException {
        log.debug("Listando último Certficado...");
        Certificado certificado = certificadoRepository.listarUltimo(usuarioId);

        if (certificado == null) {
            throw new RegraDeNegocioException("Nenhum certificado encontrado para o usuário com ID: " + usuarioId);
        }

        CertificadoDTO certificadoDTO = objectMapper.convertValue(certificado, CertificadoDTO.class);
        return certificadoDTO;
    }


    public List<CertificadoDTO> listarPorUsuario(Integer usuarioId) throws RegraDeNegocioException {
        usuarioService.findById(usuarioId);
        List<Certificado> certificados = certificadoRepository.listarPorUsuario(usuarioId);

        if (certificados.isEmpty()) {
            throw new RegraDeNegocioException("Nenhum certificado encontrado para o usuário com ID: " + usuarioId);
        }

        return certificados.stream()
                .map(certificado -> objectMapper.convertValue(certificado, CertificadoDTO.class))
                .collect(Collectors.toList());
    }
}
