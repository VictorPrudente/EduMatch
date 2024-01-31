package VS13.Squad09.EduMatch.controllers;


import VS13.Squad09.EduMatch.dtos.request.ProvaCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.ProvaDTO;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
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
    public ResponseEntity<ProvaDTO> create(@RequestBody ProvaCreateDTO provaCreateDTO, @PathVariable Integer trilha, @PathVariable Integer dificuldade) throws NaoEncontradoException, BancoDeDadosException {
        return ResponseEntity.ok(service.create(provaCreateDTO, trilha, dificuldade));
    }


}
