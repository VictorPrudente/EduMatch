package VS13.Squad09.EduMatch.controllers.interfaces;

import VS13.Squad09.EduMatch.dtos.response.LogContadorDTO;
import VS13.Squad09.EduMatch.dtos.response.LogDTO;
import VS13.Squad09.EduMatch.entities.enums.TipoLog;
import VS13.Squad09.EduMatch.exceptions.EntidadeNaoEncontradaException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

public interface ILogController {
    @Operation(summary = "Listar todos os logs", description = "Lista todos os logs do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornou a lista de logs"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping()
    public List<LogDTO> list();

    @Operation(summary = "Listar todos os logs em paginacao", description = "Lista os logs em paginacao do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornou a lista de logs"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/pageable")
    public Page<LogDTO> listPageable(@PageableDefault(size = 10, page = 0, sort = {"data"}) Pageable pageable);

    @Operation(summary = "Listar o log pelo ID", description = "Lista o log pelo ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornou o log pelo ID"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/by-id")
    public LogDTO listById(String id) throws EntidadeNaoEncontradaException;

    @Operation(summary = "Listar os logs pelo tipo de log", description = "Lista os logs pelo tipo de log do banco")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornou a lista logs pelo tipo"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/by-tipolog")
    public List<LogDTO> listByTipoLog(TipoLog tipoLog);

    @Operation(summary = "Listar os logs pelo tipo e contagem", description = "Lista os logs pelo tipo e contagem")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornou a lista de logs pelo tipo e contagem"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/group-by-tipolog-and-count")
    public List<LogContadorDTO> groupByTipoLogAndCount();

    @Operation(summary = "Listar todos os logs por data e contagem", description = "Lista todos os logs por data e contagem")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornou a lista de logs"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/group-by-date-and-count-tipolog")
    public List<LogContadorDTO> groupByDateAndCountTipoLog(String date);

    @Operation(summary = "Listar todos os logs pela data", description = "Lista todos os logs pela data")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornou a lista de logs"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/find-all-by-date")
    public List<LogDTO> listByDate(String date) throws Exception;

    @Operation(summary = "Contar todos os logs pela data", description = "Contar todos os logs pela data ")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornou a lista de logs"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/count-all-by-date")
    public Integer countLogsByDate(String date);

    @Operation(summary = "Listar todos os logs do dia", description = "Lista todos os logs do dia")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retornou a lista de logs"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/count-all-of-today")
    public Integer countLogsOfToday();
}
