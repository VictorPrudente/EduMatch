package VS13.Squad09.EduMatch.controllers;

import VS13.Squad09.EduMatch.controllers.interfaces.IEmpresaController;
import VS13.Squad09.EduMatch.dtos.usuario.response.EmpresaDTO;
import VS13.Squad09.EduMatch.services.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/parceiros")
@Tag(name = "Empresas Parceiras", description = "Rota pública")
public class EmpresaController implements IEmpresaController {

    private final UsuarioService usuarioService;

    @GetMapping()
    public ResponseEntity<List<EmpresaDTO>> listarEmpresas (){
        return ResponseEntity.ok(usuarioService.listarEmpresas());
    }
}
