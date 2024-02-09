package VS13.Squad09.EduMatch.controllers.interfaces;

import VS13.Squad09.EduMatch.dtos.ranking.RankingDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IRankingController {
    @Operation(summary = "Listar todas as Classificações de maneira paginada.", description = "Lista todas as classificações(elo) do sistema com seus respectivos usuários de maneira paginada podendo receber, ou não, um parâmetro para especificar o elo que deseja ser avaliado. Por padrão, a paginação retorna 50 usuários por página e está ordenada do elo mais alto para o mais baixo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")})
    @GetMapping
    ResponseEntity<Page<RankingDTO>> listarPorRanking(@RequestParam(required = false) String elo,
                                                      @PageableDefault(size = 50) Pageable pageable) throws Exception;
}
