package VS13.Squad09.EduMatch.controllers;

import VS13.Squad09.EduMatch.controllers.interfaces.IUsuarioController;
import VS13.Squad09.EduMatch.dtos.UsuarioCompletoRelatorioDTO;
import VS13.Squad09.EduMatch.dtos.UsuarioECertificadoRelatorioDTO;
import VS13.Squad09.EduMatch.dtos.request.LoginCreateDTO;
import VS13.Squad09.EduMatch.dtos.usuario.request.UsuarioCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.UsuarioDTO;
import VS13.Squad09.EduMatch.entities.Usuario;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<UsuarioDTO> criar(@RequestBody @Valid UsuarioCreateDTO usuario) throws Exception {
        log.info("Criando");
        return ResponseEntity.ok(usuarioService.criar(usuario));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodos() throws BancoDeDadosException {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> listarPorId(@PathVariable("id") @NotNull Integer id) throws Exception {
        return ResponseEntity.ok(usuarioService.listarPorId(id));
    }

    @GetMapping("/status/{stts}")
    public ResponseEntity<List<UsuarioDTO>> listarPorStatus(@PathVariable @NotNull Integer stts) throws Exception {
        return ResponseEntity.ok(usuarioService.listarPorStatus(stts));
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<UsuarioDTO> atualizar(@RequestBody @Valid UsuarioCreateDTO usuarioAtualizar,
                                                @PathVariable Integer idUsuario) throws Exception {
        return ResponseEntity.ok().body(usuarioService.atualizar(idUsuario, usuarioAtualizar));
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> delete(@PathVariable("idUsuario") @NotNull Integer id) throws Exception {
        usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login (@RequestBody LoginCreateDTO loginCreateDTO) throws Exception{
        return ResponseEntity.ok(usuarioService.login(loginCreateDTO));
    }

    @GetMapping("/usuario-completo/{idUsuario}")
    public ResponseEntity<UsuarioCompletoRelatorioDTO> listarUsuarioCompletoRelatorio(@PathVariable("idUsuario") @NotNull Integer idUsuario){
        return ResponseEntity.ok(usuarioService.listarUsuarioCompletoRelatorio(idUsuario));
    }

    @GetMapping("/usuario-com-certificado/{idUsuario}")
    public ResponseEntity<UsuarioECertificadoRelatorioDTO> listarUsuarioComCertificado(@PathVariable("idUsuario") @NotNull Integer idUsuario){
        return ResponseEntity.ok(usuarioService.listarUsuarioComCertificado(idUsuario));
    }

    @GetMapping("/usuario-paginado")
    public ResponseEntity<Page<Usuario>> listPaginada(@RequestParam(defaultValue = "0")
                                                          Integer paginaSolicitada,
                                                      @RequestParam(defaultValue = "10")
                                                      Integer tamanhoPagina) {
        return ResponseEntity.ok(usuarioService.listPaginadaByName(paginaSolicitada, tamanhoPagina));
    }
}
