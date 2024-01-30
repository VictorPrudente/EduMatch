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


    @Operation(summary = "Listar a Última Insignia de um Usuário", description = "Lista a última insignia obtida por um usuário específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")})
    @GetMapping("/usuario/{usuarioId}/ultimo")
    ResponseEntity<InsigniaDTO> listarUltimo(@NotNull @PathVariable("usuarioId") Integer usuarioId) throws Exception;


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
    @PostMapping("/{idUsuario}")
    ResponseEntity<InsigniaDTO> criar(@PathVariable Integer idUsuario, @Valid @RequestBody InsigniaCreateDTO insignia) throws Exception;


    @Operation(summary = "Deletar uma Insignia", description = "Deleta uma insignia específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida. Insignia deletada."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Insignia não deletada."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção. Insignia não deletada.")})
    @DeleteMapping("/{id}")
    ResponseEntity<String> deletar(@NotNull @PathVariable("id") Integer id) throws Exception;
}
