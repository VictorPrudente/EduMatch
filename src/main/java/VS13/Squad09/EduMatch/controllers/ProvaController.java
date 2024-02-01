package VS13.Squad09.EduMatch.controllers;


import VS13.Squad09.EduMatch.dtos.request.prova.ProvaStartCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.prova.ProvaStartDTO;
import VS13.Squad09.EduMatch.services.ProvaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/prova")
public class ProvaController {

    private final ProvaService service;

    @PostMapping("/{trilha}/{dificuldade}")
    public ResponseEntity<ProvaStartDTO> startTest(@RequestBody ProvaStartCreateDTO provaStartCreateDTO, @PathVariable Integer trilha, @PathVariable Integer dificuldade) throws Exception {
        return ResponseEntity.ok(service.startTest(provaStartCreateDTO, trilha, dificuldade));
    }

    @PostMapping
    public ResponseEntity<ProvaStartDTO> finishTest(@RequestBody ProvaStartCreateDTO provaStartCreateDTO) throws Exception {
        return ResponseEntity.ok(service.finishTest(provaStartCreateDTO));
    }


}
