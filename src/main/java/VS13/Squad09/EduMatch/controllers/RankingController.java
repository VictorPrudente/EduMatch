package VS13.Squad09.EduMatch.controllers;


import VS13.Squad09.EduMatch.controllers.interfaces.IRankingController;
import VS13.Squad09.EduMatch.dtos.ranking.request.RankingCreateDTO;
import VS13.Squad09.EduMatch.dtos.ranking.response.RankingDTO;
import VS13.Squad09.EduMatch.entities.Ranking;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import VS13.Squad09.EduMatch.services.RankingService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Ranking", description = "Rotas privadas")
public class RankingController implements IRankingController {

    private final RankingService rankingService;

    //OK
    @PostMapping
    public ResponseEntity<RankingDTO> criar(@Valid @RequestBody RankingCreateDTO rankingCreateDTO) throws Exception {
        return new ResponseEntity<>(rankingService.criar(rankingCreateDTO), HttpStatus.CREATED);
    }

    //OK
    @PutMapping("{idRanking}")
    public ResponseEntity<RankingDTO> atualizar(@PathVariable String elo, @Valid @RequestBody RankingCreateDTO rankingCreateDTO) throws NaoEncontradoException {
        return ResponseEntity.ok(rankingService.atualizar(elo, rankingCreateDTO));
    }

    @GetMapping
    public ResponseEntity<Page<RankingDTO>> listarPorRanking(@RequestParam(required = false) String elo,
                                                          @PageableDefault(size = 50, sort = "pontuacaoNecessaria", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {
        Page<RankingDTO> rankingDTO = rankingService.listarPorRanking(elo, pageable);
        return ResponseEntity.ok(rankingDTO);
    }

    @GetMapping("/elo")
    public ResponseEntity<List<RankingDTO>> listarRanking() throws Exception {
        return ResponseEntity.ok(rankingService.listarPorElo());
    }
}
