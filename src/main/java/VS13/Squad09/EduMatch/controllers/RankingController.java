package VS13.Squad09.EduMatch.controllers;


import VS13.Squad09.EduMatch.controllers.interfaces.IRankingController;
import VS13.Squad09.EduMatch.dtos.response.RankingDTO;
import VS13.Squad09.EduMatch.entities.enums.Elo;
import VS13.Squad09.EduMatch.services.RankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/rankings")
public class RankingController implements IRankingController {


    private final RankingService rankingService;


    @GetMapping
    public ResponseEntity<List<RankingDTO>> listarTodos() throws Exception {
        List<RankingDTO> rankingDTO = rankingService.listarTodos();
        log.debug("Todos as classificaçõess listados.");
        return new ResponseEntity<>(rankingDTO, HttpStatus.OK);
    }

    @GetMapping("/elo/{titulo}")
    public ResponseEntity<Page<RankingDTO>> listarPorRanking(@PageableDefault(size = 50) Pageable pageable, @PathVariable String titulo) throws Exception {
        Page<RankingDTO> rankingDTO = rankingService.listarPorRanking(titulo, pageable);
        return ResponseEntity.ok(rankingDTO);
    }


}
