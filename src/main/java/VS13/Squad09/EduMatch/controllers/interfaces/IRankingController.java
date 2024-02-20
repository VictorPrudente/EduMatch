package VS13.Squad09.EduMatch.controllers.interfaces;

import VS13.Squad09.EduMatch.dtos.request.RankingCreateDTO;
import VS13.Squad09.EduMatch.dtos.response.RankingDTO;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Tag(name = "Ranking", description = "Rotas privadas para qualquer tipo de usuário autenticado")
public interface IRankingController {

    @Operation(summary = "Criar um Ranking.", description = "Cadastra um novo Ranking no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação bem sucedida. Ranking cadastrado."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Ranking não cadastrado."),
            @ApiResponse(responseCode = "409", description = "Restrição de valor único violada. Ranking não cadastrado."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")})
    @PostMapping
    ResponseEntity<RankingDTO> criar(@Valid @RequestBody RankingCreateDTO rankingCreateDTO) throws Exception;

    @Operation(summary = "Atualizar um Ranking.", description = "Atualiza um Ranking do banco de dados passando por parâmetro o titulo do elo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida. Ranking atualizado."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Ranking não atualizado."),
            @ApiResponse(responseCode = "404", description = "Nenhum Ranking com o titulo fornecido foi encontrado. Ranking não atualizado."),
            @ApiResponse(responseCode = "409", description = "Restrição de valor único violada. Ranking não atualizado."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")})
    @PutMapping("{elo}")
    ResponseEntity<RankingDTO> atualizar(@PathVariable String elo, @Valid @RequestBody RankingCreateDTO rankingCreateDTO) throws NaoEncontradoException;

    }