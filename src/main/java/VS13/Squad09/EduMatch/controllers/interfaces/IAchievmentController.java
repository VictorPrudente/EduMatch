package VS13.Squad09.EduMatch.controllers.interfaces;

import VS13.Squad09.EduMatch.dtos.response.InsigniaDTO;
import VS13.Squad09.EduMatch.dtos.response.RankingDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IAchievmentController {

    @Operation(summary = "Listar uma ou todas as Insignias", description = "Retorna uma insignia com todos os seus dados caso passe um ID válido, ou uma lista com todas as insignias trazendo apenas o ID, URL da imagem e seu titulo.")
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "200", description = "Retornou a lista de certificados"),
                    @ApiResponse(responseCode = "404", description = "Nenhuma insígnia com o ID fornecido foi encontrada."),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")})
    @GetMapping("/insignias")
    ResponseEntity<List<InsigniaDTO>> listarInsignias(@RequestParam(required = false) Integer idInsignia);

    @Operation(summary = "Listar um ou todos os Rankings", description = "Retorna um ranking com todos os seus dados caso passe um ID válido, ou uma lista com todos os Rankings trazendo apenas o ID, URL da imagem e seu titulo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retornou a lista de certificados"),
            @ApiResponse(responseCode = "404", description = "Nenhum Ranking com o ID fornecido foi encontrado."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")})
    @GetMapping("/rankings")
    ResponseEntity<List<RankingDTO>> listarRankings(@RequestParam(required = false) Integer idRanking);

}
