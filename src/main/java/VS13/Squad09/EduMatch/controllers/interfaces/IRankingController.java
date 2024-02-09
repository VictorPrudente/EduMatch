package VS13.Squad09.EduMatch.controllers.interfaces;

import VS13.Squad09.EduMatch.dtos.ranking.request.RankingCreateDTO;
import VS13.Squad09.EduMatch.dtos.ranking.response.RankingDTO;
import VS13.Squad09.EduMatch.dtos.ranking.response.RankingUsuarioDTO;
import VS13.Squad09.EduMatch.exceptions.NaoEncontradoException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IRankingController {

    @Operation(summary = "Criar um Ranking.", description = "Cadastra um novo Ranking no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação bem sucedida. Ranking cadastrado."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Ranking não cadastrado."),
            @ApiResponse(responseCode = "409", description = "Restrição de valor único violada. Ranking não cadastrado."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")})
    @PostMapping
    ResponseEntity<RankingDTO> criar(@Valid @RequestBody RankingCreateDTO rankingCreateDTO) throws Exception;

    @Operation(summary = "Atualizar um Ranking.", description = "Atualiza um Ranking do banco de dados passando por parâmetro um ID válido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida. Ranking atualizado."),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso. Ranking não atualizado."),
            @ApiResponse(responseCode = "404", description = "Nenhum Ranking com o ID fornecido foi encontrado. Ranking não atualizado."),
            @ApiResponse(responseCode = "409", description = "Restrição de valor único violada. Ranking não atualizado."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")})
    @PutMapping("{idRanking}")
    ResponseEntity<RankingDTO> atualizar(@PathVariable Integer idRanking, @Valid @RequestBody RankingCreateDTO rankingCreateDTO) throws NaoEncontradoException;


    @Operation(summary = "Listar um ou todos os Rankings do sistema.", description = "Lista um ou todos os Rankings do sistema através de um parâmetro opcional (seu nome). A consulta traz apenas os atributos padrão da entidade.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")})
    @GetMapping("/elos")
    ResponseEntity<List<RankingDTO>> listarRankings(@RequestParam(required = false) String elo) throws Exception;

    @Operation(summary = "Listar um ou todos os Rankings de maneira paginada junto com os usuários pertencentes aquele elo.", description = "Lista um ou todos os Rankings do sistema com seus respectivos usuários através de um parâmetro opcional (seu nome). A consulta traz por padrão, 50 usuários por página, ordenada pela pontuação necessária dos Rankings, indo do maior para o menor. Assim como ordena os usuários daquele elo através da pontuação deles, indo do jogador com a maior pontuação, para o jogador com a menor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação bem sucedida."),
            @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção.")})
    @GetMapping
    ResponseEntity<Page<RankingUsuarioDTO>> listarPorRanking(@RequestParam(required = false) String elo,
                                                             @PageableDefault(size = 50) Pageable pageable) throws Exception;
}