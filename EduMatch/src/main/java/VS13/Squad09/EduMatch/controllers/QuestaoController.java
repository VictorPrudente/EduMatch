package VS13.Squad09.EduMatch.controllers;


import VS13.Squad09.EduMatch.controllers.interfaces.IQuestaoController;
import VS13.Squad09.EduMatch.dtos.request.QuestaoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.QuestaoDTO;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.services.QuestaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
                                             @RequestBody @Valid QuestaoCreateDTO questaoCreateDTO) throws BancoDeDadosException {
        return ResponseEntity.ok(service.update(id, questaoCreateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws BancoDeDadosException {
        return ResponseEntity.ok(service.deletar(id));
    }

    @GetMapping({"/{trilha}/{dificuldade}"})
    public ResponseEntity<QuestaoDTO> listByTrailAndDificulty(@PathVariable Integer trilha, @PathVariable Integer dificuldade) throws BancoDeDadosException {
        return ResponseEntity.ok(service.listByTrailAndDificulty(trilha, dificuldade));
    }
}