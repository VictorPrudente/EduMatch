package VS13.Squad09.EduMatch.controllers;

import VS13.Squad09.EduMatch.controllers.interfaces.IUsuarioController;
import VS13.Squad09.EduMatch.dtos.request.UsuarioCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController implements IUsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody @Valid UsuarioCreateDTO usuario) throws Exception {
        log.info("Criando");
        return ResponseEntity.ok(usuarioService.adicionar(usuario));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodos() throws BancoDeDadosException {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO>  listarPorId(@PathVariable("id") @NotNull Integer id) throws Exception {
        return ResponseEntity.ok(usuarioService.listarPorId(id));
    }

    @GetMapping("/email/{email}") //email?email=
    public ResponseEntity<UsuarioDTO> listarPorEmail(@PathVariable @NotNull String email) throws Exception {
        return ResponseEntity.ok(usuarioService.listarPorEmail(email));
    }

    @GetMapping("/status/{stts}")
    public ResponseEntity<List<UsuarioDTO>> listarPorStatus(@PathVariable @NotNull Integer stts) throws Exception {
        return ResponseEntity.ok(usuarioService.listarPorStatus(stts));
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> atualizar(@PathVariable("idUsuario") @NotNull Integer id,
                                            @RequestBody @Valid UsuarioCreateDTO usuarioAtualizar) throws Exception {
        return ResponseEntity.ok().body(usuarioService.atualizar(id, usuarioAtualizar));
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> delete(@PathVariable("idUsuario") @NotNull Integer id) throws Exception {
        usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/rankear")
    public ResponseEntity<List<UsuarioDTO>> rankearUsuarios () throws Exception{
        return ResponseEntity.ok(usuarioService.rankearUsuarios());
    }


    @GetMapping("/login/{email}/{senha}")
    public ResponseEntity<Boolean> login (@PathVariable String email, @PathVariable String senha) throws Exception{
        return ResponseEntity.ok(usuarioService.login(email, senha));
    }
}