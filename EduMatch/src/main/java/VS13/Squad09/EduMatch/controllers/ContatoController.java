package VS13.Squad09.EduMatch.controllers;

import VS13.Squad09.EduMatch.controllers.interfaces.IContatoController;
import VS13.Squad09.EduMatch.dtos.request.ContatoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.ContatoDTO;
import VS13.Squad09.EduMatch.services.ContatoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@Tag(name = "Contato")
@RequiredArgsConstructor
@RequestMapping("/contato")
public class ContatoController implements IContatoController {
    private final ContatoService contatoService;

    @GetMapping("/{idUsuario}/usuario")
    public ResponseEntity<List<ContatoDTO>> listarPorUsuario(Integer idUsuario) throws Exception {
        return new ResponseEntity<>(contatoService.listarPorUsuario(idUsuario), HttpStatus.OK);
    }

    @PostMapping("/{idUsuario}")
    public ResponseEntity<ContatoDTO> salvar(@PathVariable Integer idUsuario,
                                             @Valid @RequestBody ContatoCreateDTO contatoCreateDTO) throws Exception {
        log.debug("Criando contato...");
        ContatoDTO contatoCriado = contatoService.salvar(idUsuario, contatoCreateDTO);

        log.debug("Contato criado!");

        return new ResponseEntity<>(contatoCriado, HttpStatus.CREATED);
    }

    @PutMapping("/{idContato}")
    public ResponseEntity<ContatoDTO> atualizar(@PathVariable Integer idContato,
                                                @Valid @RequestBody ContatoCreateDTO contatoCreateDTO) throws Exception {
        log.debug("Atualizando contato...");
        ContatoDTO contatoAtualizado = contatoService.atualizar(idContato, contatoCreateDTO);

        log.debug("Contato atualizado!");
        return new ResponseEntity<>(contatoAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{idContato}")
    public ResponseEntity<Void> deletar(@PathVariable Integer idContato) throws Exception {
        log.debug("Deletando contato...");
        contatoService.deletar(idContato);

        log.debug("Contato deletado!");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
