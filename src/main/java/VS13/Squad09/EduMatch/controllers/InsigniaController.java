package VS13.Squad09.EduMatch.controllers;


import VS13.Squad09.EduMatch.controllers.interfaces.IInsigniasController;
import VS13.Squad09.EduMatch.dtos.request.InsigniaCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.InsigniaDTO;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import VS13.Squad09.EduMatch.services.InsigniaService;
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
@RequiredArgsConstructor
@RequestMapping("/insignia")
public class InsigniaController implements IInsigniasController {

    private final InsigniaService insigniaService;

    @PostMapping
    public ResponseEntity<InsigniaDTO> criar(@Valid @RequestBody InsigniaCreateDTO insignia) throws Exception {
        return new ResponseEntity<>(insigniaService.create(insignia), HttpStatus.CREATED);
    }

    @PutMapping("{idInsignia}")
    public ResponseEntity<InsigniaDTO> atualizar(@PathVariable Integer idInsignia, @Valid @RequestBody InsigniaCreateDTO insignia) throws NaoEncontradoException {
        return ResponseEntity.ok(insigniaService.update(idInsignia, insignia));
    }

    @GetMapping
    public ResponseEntity<List<InsigniaDTO>> listarPorUsuario(@RequestParam Integer usuario,
                                                         @RequestParam(required = false) Integer insignia) {
        return ResponseEntity.ok(insigniaService.findByUser(usuario, insignia));
    }
}
