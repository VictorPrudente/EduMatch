package VS13.Squad09.EduMatch.controllers;

import VS13.Squad09.EduMatch.controllers.interfaces.IEmpresaController;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.services.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/parceiros")
public class EmpresaController implements IEmpresaController {

    private final UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<Set<UsuarioDTO>> listarEmpresas () throws Exception {
        return ResponseEntity.ok(usuarioService.listarEmpresas());
    }
}
