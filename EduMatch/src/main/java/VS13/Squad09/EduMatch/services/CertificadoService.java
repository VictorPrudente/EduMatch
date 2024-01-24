package VS13.Squad09.EduMatch.services;

import VS13.Squad09.EduMatch.dtos.request.CertificadoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.CertificadoDTO;
import VS13.Squad09.EduMatch.entities.Certificado;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.repositories.CertificadoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class CertificadoService {

    private final CertificadoRepository certificadoRepository;
    private final ObjectMapper objectMapper;

    public CertificadoDTO criar(CertificadoCreateDTO certificado) throws Exception {
        log.debug("Criando Certificado...");

        Certificado certificadoEntity = objectMapper.convertValue(certificado, Certificado.class);

        certificadoEntity = certificadoRepository.adicionar(certificadoEntity);

        CertificadoDTO certificadoDTO = objectMapper.convertValue(certificadoEntity, CertificadoDTO.class);

        return certificadoDTO;
    }

    public CertificadoDTO deletar(int id) throws Exception {
        log.debug("Deletando certificado...");

        certificadoRepository.remover(id);

        Certificado certificadoRecuperado = getCertificado(id);

        certificadoRepository.remover(certificadoRecuperado.getId());

        CertificadoDTO certificadoDTO = objectMapper.convertValue(certificadoRecuperado, CertificadoDTO.class);

        return certificadoDTO;

    }

    public List<CertificadoDTO> listarTodos() throws Exception {
        log.debug("Listando Certificados...");
        return certificadoRepository.listar().stream().map(certificado ->
                        objectMapper.convertValue(certificado, CertificadoDTO.class))
                .collect(Collectors.toList());
    }

    public CertificadoDTO listarUltimo(Usuario usuario) throws Exception {
        log.debug("Listando último Certficado...");
        Certificado certificado = certificadoRepository.listarUltimo(usuario);
        CertificadoDTO certificadoDTO = objectMapper.convertValue(certificado, CertificadoDTO.class);
        return certificadoDTO;
    }


    public CertificadoDTO listarPorUsuario(Usuario usuario) throws Exception {
        List<Certificado> certificados = certificadoRepository.listarPorUsuario(usuario);
        CertificadoDTO certificadoDTO = objectMapper.convertValue(certificados, CertificadoDTO.class);
        return certificadoDTO;
    }

    public Certificado getCertificado(Integer id) throws Exception {
        return certificadoRepository.listar().stream()
                .filter(certificado -> certificado.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new Exception("Certificado não encontrado"));
    }
}
