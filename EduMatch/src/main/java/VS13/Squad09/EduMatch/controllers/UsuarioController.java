package VS13.Squad09.EduMatch.controllers;

import VS13.Squad09.EduMatch.dtos.request.UsuarioCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody @Valid UsuarioCreateDTO usuario)throws BancoDeDadosException {
        return ResponseEntity.ok(usuarioService.salvar(usuario));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodos() throws BancoDeDadosException {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO>  listarPorId(@PathVariable("id") @NotNull Integer id) throws Exception {
        return ResponseEntity.ok(usuarioService.listarPorId(id));
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> atualizar(@PathVariable("idUsuario") @NotNull Integer id,
                                            @RequestBody @Valid UsuarioCreateDTO usuarioAtualizar) throws BancoDeDadosException {
        return ResponseEntity.ok().body(usuarioService.atualizar(id, usuarioAtualizar));
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> delete(@PathVariable("idUsuario") @NotNull Integer id) throws BancoDeDadosException {
        usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/rankear")
    public ResponseEntity<List<UsuarioDTO>> rankearUsuarios () throws Exception{
        return ResponseEntity.ok(usuarioService.rankearUsuarios());
    }
}
