package VS13.Squad09.EduMatch.controllers;


import VS13.Squad09.EduMatch.controllers.interfaces.ICertificadoController;
import VS13.Squad09.EduMatch.dtos.request.CertificadoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.CertificadoDTO;
import VS13.Squad09.EduMatch.services.CertificadoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/certificado")
@Validated
@Slf4j
public class CertificadoController implements ICertificadoController {

    private final CertificadoService certificadoService;

    @GetMapping
    public ResponseEntity<List<CertificadoDTO>> listarTodos() throws Exception {
        List<CertificadoDTO> certificadoDTOS = certificadoService.listarTodos();
        log.debug("Todos os certificados listados.");
        return new ResponseEntity<>(certificadoDTOS, HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioId}/ultimo")
    public ResponseEntity<CertificadoDTO> listarUltimo(@NotNull @PathVariable("usuarioId") Integer usuarioId) throws Exception {
        CertificadoDTO certificadoDTO = certificadoService.listarUltimo(usuarioId);
        log.debug("Ultimo certificado do usuário.");
        return new ResponseEntity<>(certificadoDTO, HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<CertificadoDTO> listarPorUsuario(@NotNull @PathVariable("usuarioId") Integer usuarioId) throws Exception {
        CertificadoDTO certificadoDTO = certificadoService.listarPorUsuario(usuarioId);
        log.debug("Certificados do usuário listados.");
        return new ResponseEntity<>(certificadoDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CertificadoDTO> criar(@Valid @RequestBody CertificadoCreateDTO certificado) throws Exception {
        log.debug("Certificado Criado.");
        return new ResponseEntity<>(certificadoService.criar(certificado), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@NotNull @PathVariable("id") Integer id) throws Exception {
        log.debug("Certificado Deletado.");
        certificadoService.deletar(id);
        return new ResponseEntity<>("Certificado deletado com sucesso", HttpStatus.OK);
    }
}
