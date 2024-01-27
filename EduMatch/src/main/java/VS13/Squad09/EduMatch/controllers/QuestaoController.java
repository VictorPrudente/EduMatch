package VS13.Squad09.EduMatch.controllers;


import VS13.Squad09.EduMatch.controllers.interfaces.IQuestaoController;
import VS13.Squad09.EduMatch.dtos.request.QuestaoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.QuestaoDTO;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import VS13.Squad09.EduMatch.services.QuestaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/questoes")
public class QuestaoController implements IQuestaoController {

    private final QuestaoService service;


    @PostMapping
    public ResponseEntity<QuestaoDTO> create(@RequestBody @Valid QuestaoCreateDTO questaoCreateDTO) throws BancoDeDadosException {
        return ResponseEntity.ok(service.create(questaoCreateDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<QuestaoDTO> update(@PathVariable Integer id,
                                             @RequestBody @Valid QuestaoCreateDTO questaoCreateDTO) throws BancoDeDadosException, NaoEncontradoException {
        return ResponseEntity.ok(service.update(id, questaoCreateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<QuestaoDTO> delete(@PathVariable Integer id) throws BancoDeDadosException, NaoEncontradoException {
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<QuestaoDTO> findById(@PathVariable Integer id) throws BancoDeDadosException, NaoEncontradoException {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping({"/{trilha}/{dificuldade}"})
    public ResponseEntity<QuestaoDTO> findByTrailAndDificulty(@PathVariable Integer trilha, @PathVariable Integer dificuldade) throws BancoDeDadosException, NaoEncontradoException {
        return ResponseEntity.ok(service.findByTrailAndDificulty(trilha, dificuldade));
    }

    @GetMapping({"/all/{trilha}/{dificuldade}"})
    public ResponseEntity<List<QuestaoDTO>> findAllByTrailAndDificulty(@PathVariable Integer trilha, @PathVariable Integer dificuldade) throws BancoDeDadosException {
        return ResponseEntity.ok(service.findAllByTrailAndDificulty(trilha, dificuldade));
    }


    @GetMapping({"/{trilha}/all"})
    public ResponseEntity<List<QuestaoDTO>> findAllByTrail(@PathVariable Integer trilha) throws BancoDeDadosException {
        return ResponseEntity.ok(service.findAllByTrail(trilha));
    }

    @GetMapping({"/ativas"})
    public ResponseEntity<List<QuestaoDTO>> findAllActive() throws BancoDeDadosException {
        log.info("Buscando questoes na controller.");
        return ResponseEntity.ok(service.findAllActive());
    }
}