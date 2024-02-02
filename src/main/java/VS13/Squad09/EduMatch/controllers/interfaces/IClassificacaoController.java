package VS13.Squad09.EduMatch.controllers.interfaces;

import VS13.Squad09.EduMatch.dtos.response.RankingDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface IClassificacaoController {
    @Operation(summary = "Listar todas as Classificações", description = "Lista todas as classificações cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")})
    @GetMapping
    ResponseEntity<List<RankingDTO>> listarTodos() throws Exception;
}
