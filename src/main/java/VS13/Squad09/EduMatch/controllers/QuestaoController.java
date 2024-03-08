package VS13.Squad09.EduMatch.controllers;


import VS13.Squad09.EduMatch.controllers.interfaces.IQuestaoController;
import VS13.Squad09.EduMatch.dtos.request.QuestaoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.QuestaoDTO;
import VS13.Squad09.EduMatch.exceptions.BancoDeDadosException;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import VS13.Squad09.EduMatch.services.QuestaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/questoes")
public class QuestaoController implements IQuestaoController{

    private final QuestaoService service;


    @PostMapping
    public ResponseEntity<QuestaoDTO> create(@RequestBody @Valid QuestaoCreateDTO questaoCreateDTO) throws BancoDeDadosException {
        return ResponseEntity.ok(service.create(questaoCreateDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<QuestaoDTO> update(@PathVariable Integer id,
                                             @RequestBody @Valid QuestaoCreateDTO questaoCreateDTO) throws NaoEncontradoException {
        return ResponseEntity.ok(service.update(id, questaoCreateDTO));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<QuestaoDTO> findById(@PathVariable Integer id) throws NaoEncontradoException {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<QuestaoDTO>> listarQuestoes(
            @RequestParam Integer status,
            @RequestParam(required = false) Integer trilha,
            @RequestParam(required = false) Integer dificuldade,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {

        Sort.Direction direction = Sort.DEFAULT_DIRECTION;

        Pageable pageable = sort != null ?
                PageRequest.of(page, size, direction, sort) :
                PageRequest.of(page, size);

        return ResponseEntity.ok(service.questoesPage(trilha, dificuldade, status, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<QuestaoDTO> delete(@PathVariable Integer id) throws NaoEncontradoException {
        return ResponseEntity.ok(service.delete(id));
    }
}