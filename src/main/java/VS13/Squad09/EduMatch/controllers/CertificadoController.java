package VS13.Squad09.EduMatch.controllers;


import VS13.Squad09.EduMatch.controllers.interfaces.ICertificadoController;
import VS13.Squad09.EduMatch.dtos.UsuarioECertificadoRelatorioDTO;
import VS13.Squad09.EduMatch.dtos.request.CertificadoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.CertificadoDTO;
import VS13.Squad09.EduMatch.services.CertificadoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Validated
@RestController
@Tag(name="Certificado", description = "Endpoint do CRUD de Certificado")
@RequiredArgsConstructor
@RequestMapping("/certificado")
public class CertificadoController implements ICertificadoController {

    private final CertificadoService certificadoService;

    @GetMapping
    public ResponseEntity<List<CertificadoDTO>> listarTodos() throws Exception {
        List<CertificadoDTO> certificadoDTOs = certificadoService.listarTodos();
        log.info("Todos os certificados listados.");
        return new ResponseEntity<>(certificadoDTOs, HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<UsuarioECertificadoRelatorioDTO> listarPorUsuario(@NotNull @PathVariable("usuarioId") Integer usuarioId) throws Exception {
        UsuarioECertificadoRelatorioDTO usuarioECertificadoRelatorioDTO = certificadoService.listarPorUsuario(usuarioId);
        log.info("Certificados do usuário listados.");
        return new ResponseEntity<>(usuarioECertificadoRelatorioDTO, HttpStatus.OK);
    }

    @PostMapping("/{idUsuario}")
    public ResponseEntity<CertificadoDTO> criar(@PathVariable Integer idUsuario, @Valid @RequestBody CertificadoCreateDTO certificado) throws Exception {
        log.info("Certificado Criado.");
        return new ResponseEntity<>(certificadoService.criar(idUsuario, certificado), HttpStatus.CREATED);
    }
}
