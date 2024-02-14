package VS13.Squad09.EduMatch.controllers;


import VS13.Squad09.EduMatch.controllers.interfaces.IProvaController;
import VS13.Squad09.EduMatch.dtos.request.prova.ProvaFinishCreateDTO;
import VS13.Squad09.EduMatch.dtos.request.prova.ProvaStartCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.prova.ProvaFinishDTO;
import VS13.Squad09.EduMatch.dtos.response.prova.ProvaStartDTO;
import VS13.Squad09.EduMatch.services.ProvaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/prova")
@Tag(name = "Provas", description = "Rotas privadas")
public class ProvaController implements IProvaController {

    private final ProvaService service;

    @PostMapping("/start")
    public ResponseEntity<ProvaStartDTO> startTest(@RequestBody ProvaStartCreateDTO provaStartCreateDTO) throws Exception {
        return ResponseEntity.ok(service.startTest(provaStartCreateDTO));
    }

    @PutMapping("/{idProva}")
    public ResponseEntity<ProvaFinishDTO> finishTest(@PathVariable Integer idProva, @RequestBody ProvaFinishCreateDTO prova) throws Exception {
        return ResponseEntity.ok(service.finishTest(idProva,prova));
    }
}
