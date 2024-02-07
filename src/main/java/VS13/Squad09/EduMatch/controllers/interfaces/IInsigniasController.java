package VS13.Squad09.EduMatch.controllers.interfaces;

import VS13.Squad09.EduMatch.dtos.request.InsigniaCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.InsigniaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface IInsigniasController {

    @Operation(summary = "Listar todas as Insignias", description = "Lista todas as insignias cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")})
    @GetMapping
    ResponseEntity<List<InsigniaDTO>> listarTodos() throws Exception;

    @Operation(summary = "Listar Todas as Insignias de um Usuário", description = "Lista todas as insignias obtidas por um usuário específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")})
    @GetMapping("/usuario/{usuarioId}")
    ResponseEntity<InsigniaDTO> listarPorUsuario(@NotNull @PathVariable("usuarioId") Integer usuarioId) throws Exception;

    @Operation(summary = "Criar uma Insignia", description = "Cria uma nova insignia para um usuário específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida. Insignia cadastrada."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Insignia não cadastrada."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção. Insignia não cadastrada.")})
    @PostMapping
    ResponseEntity<InsigniaDTO> criar(@Valid @RequestBody InsigniaCreateDTO insignia) throws Exception;
}
