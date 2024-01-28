package VS13.Squad09.EduMatch.controllers;

import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/parceiros")
public class EmpresaController {

    private final UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<List<UsuarioDTO>> listarEmpresas () throws Exception{
        return ResponseEntity.ok(usuarioService.listarEmpresas());
    }
}
