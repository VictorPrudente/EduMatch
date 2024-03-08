package VS13.Squad09.EduMatch.controllers;


import VS13.Squad09.EduMatch.controllers.interfaces.IAchievmentController;
import VS13.Squad09.EduMatch.dtos.response.InsigniaDTO;
import VS13.Squad09.EduMatch.dtos.response.RankingDTO;
import VS13.Squad09.EduMatch.services.InsigniaService;
import VS13.Squad09.EduMatch.services.RankingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/achievments")
@Tag(name = "Achievments", description = "Rota pública")
public class AchievmentsController implements IAchievmentController {

    private final InsigniaService insigniaService;
    private final RankingService rankingService;


    @GetMapping("/insignias")
    public ResponseEntity<List<InsigniaDTO>> listarInsignias(@RequestParam(required = false) Integer idInsignia){
        return ResponseEntity.ok(insigniaService.findInsignias(idInsignia));
    }

    @GetMapping("/rankings")
    public ResponseEntity<List<RankingDTO>> listarRankings(@RequestParam(required = false) Integer idRanking){
        return ResponseEntity.ok(rankingService.findAll(idRanking));
    }
}
