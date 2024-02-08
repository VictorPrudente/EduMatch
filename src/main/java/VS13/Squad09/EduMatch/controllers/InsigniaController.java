package VS13.Squad09.EduMatch.controllers;


import VS13.Squad09.EduMatch.controllers.interfaces.IInsigniasController;
import VS13.Squad09.EduMatch.dtos.insignia.request.InsigniaCreateDTO;
import VS13.Squad09.EduMatch.dtos.insignia.response.InsigniaDetailedDTO;
import VS13.Squad09.EduMatch.services.InsigniaService;
import io.swagger.v3.oas.annotations.Hidden;
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
@Tag(name = "Insignias", description = "EndPoints da Insignia")
@RequiredArgsConstructor
@RequestMapping("/insignia")
public class InsigniaController implements IInsigniasController {


    private final InsigniaService insigniaService;

    @PostMapping
    public ResponseEntity<InsigniaDetailedDTO> criar(@Valid @RequestBody InsigniaCreateDTO insignia) throws Exception {
        return new ResponseEntity<>(insigniaService.criar(insignia), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Object>> listarInsignias(@RequestParam(required = false) Integer id) throws Exception {
        return ResponseEntity.ok(insigniaService.listarInsignias(id));
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<Object>> listarPorUsuario(@RequestParam Integer usuario,
                                                         @RequestParam(required = false) Integer insignia) throws Exception {
        return ResponseEntity.ok(insigniaService.listarPorUsuario(usuario, insignia));
    }

    @Hidden
    @GetMapping("/descricao/{texto}")
    public ResponseEntity<InsigniaDetailedDTO> acharPorTag(@PathVariable String texto) throws Exception {
        InsigniaDetailedDTO insigniaDetailedDTO = insigniaService.acharPorTag(texto);
        return new ResponseEntity<>(insigniaDetailedDTO, HttpStatus.OK);
    }

    @Hidden
    @PostMapping("{idUsuario}/{idInsignia}")
    public ResponseEntity<InsigniaDetailedDTO> addUsuario(@PathVariable Integer idUsuario, @PathVariable Integer idInsignia) throws Exception {
        return ResponseEntity.ok(insigniaService.addUsuario(idUsuario, idInsignia));
    }

}
