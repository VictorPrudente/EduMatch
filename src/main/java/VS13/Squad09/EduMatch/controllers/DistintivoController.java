package VS13.Squad09.EduMatch.controllers;


import VS13.Squad09.EduMatch.dtos.response.DistintivoDTO;
import VS13.Squad09.EduMatch.services.DistintivoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/distintivos")
public class DistintivoController {


    private final DistintivoService distintivosService;


    @GetMapping
    public ResponseEntity<Page<DistintivoDTO>> listarTodos(@PageableDefault(size = 10, sort = {"tipo"}) Pageable pageable ) throws Exception {
        Page<DistintivoDTO> distintivos = distintivosService.findAll(pageable);
        return ResponseEntity.ok(distintivos);
    }

}
