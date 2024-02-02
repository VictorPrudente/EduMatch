package VS13.Squad09.EduMatch.controllers;


import VS13.Squad09.EduMatch.controllers.interfaces.IClassificacaoController;
import VS13.Squad09.EduMatch.dtos.request.ClassificacaoCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.ClassificacaoDTO;
import VS13.Squad09.EduMatch.services.ClassificacaoService;
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
@RequestMapping("/rankings")
public class ClassificacaoController implements IClassificacaoController {


    private final ClassificacaoService classificacaoService;


    @GetMapping
    public ResponseEntity<List<ClassificacaoDTO>> listarTodos() throws Exception {
        List<ClassificacaoDTO> classificacaoDTO = classificacaoService.listarTodos();
        log.debug("Todos os classificacaos listados.");
        return new ResponseEntity<>(classificacaoDTO, HttpStatus.OK);
    }

}
