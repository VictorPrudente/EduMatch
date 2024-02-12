package VS13.Squad09.EduMatch.controllers;


import VS13.Squad09.EduMatch.controllers.interfaces.IInsigniasController;
import VS13.Squad09.EduMatch.dtos.request.InsigniaCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.InsigniaDTO;
import VS13.Squad09.EduMatch.services.InsigniaService;
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
@RequiredArgsConstructor
@RequestMapping("/insignia")
@Tag(name = "Insignias", description = "Rotas ADM")
public class InsigniaController implements IInsigniasController {

    private final InsigniaService insigniaService;

    @PostMapping
    public ResponseEntity<InsigniaDTO> criar(@Valid @RequestBody InsigniaCreateDTO insignia) throws Exception {
        return new ResponseEntity<>(insigniaService.criar(insignia), HttpStatus.CREATED);
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<InsigniaDTO>> listarPorUsuario(@RequestParam Integer usuario,
                                                         @RequestParam(required = false) Integer insignia) throws Exception {
        return ResponseEntity.ok(insigniaService.listarPorUsuario(usuario, insignia));
    }
}
