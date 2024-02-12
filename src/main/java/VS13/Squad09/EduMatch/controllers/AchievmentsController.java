package VS13.Squad09.EduMatch.controllers;


import VS13.Squad09.EduMatch.dtos.response.InsigniaDTO;
import VS13.Squad09.EduMatch.services.InsigniaService;
import VS13.Squad09.EduMatch.services.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/achievments")
public class AchievmentsController {

    private InsigniaService insigniaService;
    private RankingService rankingService;


//    @GetMapping("/insignias")
//    public ResponseEntity<List<InsigniaDTO>>
}
