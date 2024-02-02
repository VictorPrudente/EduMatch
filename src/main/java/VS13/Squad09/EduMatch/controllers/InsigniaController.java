package VS13.Squad09.EduMatch.controllers;


import VS13.Squad09.EduMatch.controllers.interfaces.IInsigniasController;
import VS13.Squad09.EduMatch.dtos.request.InsigniaCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.InsigniaDTO;
import VS13.Squad09.EduMatch.services.InsigniaService;
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
@RequiredArgsConstructor
@RequestMapping("/insignia")
public class InsigniaController implements IInsigniasController {


    private final InsigniaService insigniaService;

    @GetMapping("/todas")
    public ResponseEntity<List<InsigniaDTO>> listarTodos() throws Exception {
        List<InsigniaDTO> insigniaDTO = insigniaService.listarTodas();
        log.debug("Todos os insignias listados.");
        return new ResponseEntity<>(insigniaDTO, HttpStatus.OK);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<InsigniaDTO> listarPorUsuario(@NotNull @PathVariable("usuarioId") Integer usuarioId) throws Exception {
        InsigniaDTO insigniaDTO = insigniaService.listarPorUsuario(usuarioId);
        log.debug("Insignias do usuário listados.");
        return new ResponseEntity<>(insigniaDTO, HttpStatus.OK);
    }

    @PostMapping("/{idUsuario}")
    public ResponseEntity<InsigniaDTO> criar(@PathVariable Integer idUsuario, @Valid @RequestBody InsigniaCreateDTO insignia) throws Exception {
        log.debug("Insignia Criado.");
        return new ResponseEntity<>(insigniaService.criar(idUsuario, insignia), HttpStatus.CREATED);
    }

}
