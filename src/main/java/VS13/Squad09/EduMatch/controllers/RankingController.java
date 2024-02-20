package VS13.Squad09.EduMatch.controllers;


import VS13.Squad09.EduMatch.controllers.interfaces.IRankingController;
import VS13.Squad09.EduMatch.dtos.request.RankingCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.RankingDTO;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import VS13.Squad09.EduMatch.services.RankingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/rankings")
public class RankingController implements IRankingController {

    private final RankingService rankingService;

    //OK
    @PostMapping
    public ResponseEntity<RankingDTO> criar(@Valid @RequestBody RankingCreateDTO rankingCreateDTO) throws Exception {
        return new ResponseEntity<>(rankingService.create(rankingCreateDTO), HttpStatus.CREATED);
    }

    //OK
    @PutMapping("{idRanking}")
    public ResponseEntity<RankingDTO> atualizar(@PathVariable String elo, @Valid @RequestBody RankingCreateDTO rankingCreateDTO) throws NaoEncontradoException {
        return ResponseEntity.ok(rankingService.update(elo, rankingCreateDTO));
    }

    @GetMapping("/elo")
    public ResponseEntity<List<RankingDTO>> listarRanking() throws Exception {
        return ResponseEntity.ok(rankingService.listarPorElo());
    }
}
